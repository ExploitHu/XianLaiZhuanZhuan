package biao.im.controller;

import biao.im.dao.DStrandedMessage;
import biao.im.message.CSMessage;
import biao.im.message.StrandedMessage;
import biao.im.time.GetTime;
import biao.im.tool.DelayTask;
import biao.im.tool.SynchronizedByKey;
import biao.im.tool.Tool;
import biao.im.tool.UserLinkMessage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/myWs/{userId}/{words}")
@RestController
public class WsServerEndpoint {

    //定时检查消息是否发送成功，失败并重发
    @Resource
    private DelayTask delayTask = new DelayTask();

    static public List<String> md5 = new ArrayList<String>();

    //连接总数
    static private int sum = 0;
    //用线程安全的map来保存当前用户
    private static Map<String, List<UserLinkMessage>> onLineUsers = new ConcurrentHashMap<>();
    //声明一个session对象，通过该对象可以发送消息给指定用户，不能设置为静态，每个ChatEndPoint有一个session才能区分.(websocket的session)
    private Session session;
    //用户ID
    private String userId;

    public Session getSession() {
        return session;
    }

    //@Autowired
    //SAddMessage sAddMessage;

    private static DStrandedMessage dStrandedMessage;
    @Autowired
    public void setRepository(DStrandedMessage dStrandedMessage) {
        WsServerEndpoint.dStrandedMessage = dStrandedMessage;
    }

    private static SynchronizedByKey synchronizedByKey;
    @Autowired
    public void setRepository(SynchronizedByKey synchronizedByKey) {
        WsServerEndpoint.synchronizedByKey = synchronizedByKey;
    }

    //连接成功
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId, @PathParam("words") String words) {


        System.out.println("连接成功！");
        if (userId == null || words == null) {
            try {
                this.session.getBasicRemote().sendText("websocket连接失败，错误代码：001");
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            try {
                session.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            //判断usId&words口令是否正确  不正确调用close()方法
        }

        this.session = session;
        this.userId = userId;

        /*
        //已有设备登录此id连接  关闭之前设备并且发送报错信息
        if (onLineUsers.get(userId) != null) {
            WsServerEndpoint wsServerEndpoint = onLineUsers.get(userId);
            String str = "新设备连接，此设备断开连接";

            try {
                //获取服务器此刻时间
                String strTime = GetTime.getWebsiteDatetime("http://www.ntsc.ac.cn") + " [中国科学院国家授时中心]";
                System.out.println(strTime);
                strTime = GetTime.formattingTime(strTime);
                System.out.println(GetTime.formattingTime(strTime));
                wsServerEndpoint.session.getBasicRemote().sendText("{\"isSystem\":true,\"fromName\":null,\"time\":\"" + strTime + "\",\"message\":\"" + str + "\"}");
                try {
                    wsServerEndpoint.session.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } catch (Exception io) {
                io.printStackTrace();
            }

            onLineUsers.remove(userId);
        }


        onLineUsers.put(this.userId, this);

         */
        this.changeOnlineUsers(true);


        List<StrandedMessage> sMList = dStrandedMessage.getStrandedMessage(Integer.parseInt(this.userId));

        for (int i = 0 ; i< sMList.size();i++){
            String strTime = GetTime.formattingTime(sMList.get(i).getDia_time());

            CSMessage csMessage = new CSMessage();
            csMessage.setSystem(false);
            csMessage.setSendId(sMList.get(i).getU_id1());
            csMessage.setReId(sMList.get(i).getU_id2());
            csMessage.setMessage(sMList.get(i).getDia_message());
            csMessage.setMd5(sMList.get(i).getMd5());
            csMessage.setSendTime(sMList.get(i).getDia_time());
            String resultMessage =  csMessage.toJsonObject().toString();

            try {
                List<UserLinkMessage> tempULM = new ArrayList<>();

                this.sendMessage(this,resultMessage);
                //this.session.getBasicRemote().sendText(MessageUtils.getMessage(resultMessage));
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }


        WsServerEndpoint.sum++;
        System.out.println("连接成功 第" + WsServerEndpoint.sum + "个    " + this.userId);

        //向所有用户发送上线信息包括自己（未完善）
        //String message = MessageUtils.getMessage(true,null,getNames());
        //this.broadcastAllUsers(message);
    }

    private void broadcastAllUsers(String message) {
        Set<String> names = onLineUsers.keySet();
        for (String name : names) {
            for (UserLinkMessage userLinkMessage:onLineUsers.get(name)){
                //WsServerEndpoint wsServerEndpoint = onLineUsers.get(name);
                try {
                    userLinkMessage.getWs().session.getBasicRemote().sendText(message);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        }
    }

    private Set<String> getNames() {
        return onLineUsers.keySet();
    }

    //连接关闭
    @OnClose
    public void onClose(Session session) {
        WsServerEndpoint.sum--;
        System.out.println(this.userId.toString() + "断开连接");
        //把关闭的WsServerEndpoint从onLineUsers踢出
        this.changeOnlineUsers(false);

    }

    //接收到消息
    @OnMessage
    public String onMsg(String message) {

        //System.out.println(message);

        //获取服务器此刻时间 [中国科学院国家授时中心]
        String strTime = GetTime.getWebsiteDatetime("http://www.ntsc.ac.cn") ;
        //格式化时间例如  2021-11-02 16:15:12 [中国科学院国家授时中心]  -》  20211102161512
        strTime = GetTime.formattingTime(strTime);

        //处理message转为消息对象
        JSONObject jsonObject = JSONObject.parseObject(message);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.toString());
        CSMessage csMessage =JSONObject.toJavaObject(jsonObject1,CSMessage.class);
        csMessage.setSendTime(strTime);


        //return
        CSMessage csMessageResult = new CSMessage(true,csMessage.getSendId(),csMessage.getMd5(),strTime);

        //给系统处理的消息
        if (csMessage.isIsSystem() == true) {
            return this.processingSystemMessages(csMessage,strTime,message,csMessageResult);
        }

        //用户给自己发送消息
        if (csMessage.getReId().equals(this.userId)) {
            return this.userSendMessageForMyself(message, csMessageResult);
        }

        System.out.println("接收人：");
        System.out.println(csMessage.getReId());

        //帖子消息（用户对帖子，帖子发布者对用户）
        if((csMessage.getReId().charAt(0)=='T')||(csMessage.getReId().charAt(0)=='F')){
            this.userAndPost(csMessage);
        }

        //用户发送消息给离线用户  存储消息到数据库  用户上线后接收
        List<UserLinkMessage> listULM = onLineUsers.get(csMessage.getReId());
        if (listULM == null) {
            return this.snedOffUser(csMessage,csMessageResult);
        }

        System.out.println("向用户" + csMessage.getReId() + "发送：" + csMessage.toJsonObject().toString());


        try {
            //对用户发送一般消息
            this.sendMessage(listULM,csMessage.toJsonObject().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //收到消息返回给客户端相关信息
        return csMessageResult.toJsonObject().toString();

    }

    //非正常关闭
    @OnError
    public void onError(Session session, Throwable error) {
        String str = "非正常关闭闭ID：" + this.userId.toString() + "  错误信息：" + error.toString();
        System.out.println(str);
        this.changeOnlineUsers(false);
    }

    //发送消息(多连接)
    public void sendMessage(List<UserLinkMessage> listULM ,String message) throws IOException {
        System.out.println("-----------time----------");
        System.out.println(message);
        System.out.println("-----------time----------");

        for (UserLinkMessage uLM: listULM){
            uLM.getWs().session.getBasicRemote().sendText(message);
        }


        /*旧重发机制

        //在带验证发送消息成功序列中添加一条消息编码，用来验证消息发送成功
        md5.add(rMessage.getMd5());

        delayTask.toDetectTheRetransmission(rMessage, wSE, 2);


         */

    }

    //发送消息 单连接
    public void sendMessage(WsServerEndpoint wsServerEndpoint,String message) throws IOException {
        System.out.println("-----------time----------");
        System.out.println(message);
        System.out.println("-----------time----------");

        wsServerEndpoint.session.getBasicRemote().sendText(message);



        /*旧重发机制

        //在带验证发送消息成功序列中添加一条消息编码，用来验证消息发送成功
        md5.add(rMessage.getMd5());

        delayTask.toDetectTheRetransmission(rMessage, wSE, 2);


         */

    }

    /**
     * 添加删除onLineUsers 线程锁
     * @param delAdd 删除还是添加  true 添加  |  false 删除
     */
    void changeOnlineUsers(boolean delAdd){
        synchronizedByKey.exec(this.userId,()->{
            List<UserLinkMessage> listULM = WsServerEndpoint.onLineUsers.get(this.userId);
            if(delAdd){
                UserLinkMessage tempULM = new UserLinkMessage();
                tempULM.setWs(this);
                if(listULM == null){
                    List<UserLinkMessage> tempListULM = new ArrayList<>();
                    tempListULM.add(tempULM);
                    WsServerEndpoint.onLineUsers.put(this.userId,tempListULM);
                }else{
                    listULM.add(tempULM);
                }
            }else{
                for (UserLinkMessage tempULM:listULM){
                    if(tempULM.getWs() == this){
                        listULM.remove(tempULM);
                    }
                }
                if (listULM.size() == 0){
                    WsServerEndpoint.onLineUsers.remove(this.userId);
                }
            }
        });

    }

    //处理系统消息
    private String processingSystemMessages(CSMessage csMessage,String strTime,String message,CSMessage csMessageResult){
        //心跳连接回复
        if (csMessage.getMessage().equals("心跳连接")) {
            CSMessage tempCsMessage = new CSMessage(strTime,"心跳回复");
            JSONObject jsonObject = new JSONObject();

            //获取未读消息数量（被@）
            jsonObject.put("gToBeRead",dStrandedMessage.getGTobeRead(this.userId));
            jsonObject.put("bToBeRead",dStrandedMessage.getBTobeRead(this.userId));

            tempCsMessage.setOther(jsonObject);
            String xintiao = tempCsMessage.toJsonObject().toString();
            return xintiao;
        }

        //客户端收到消息发出回应
        if (csMessage.getMessage().equals("respond")) {
            //接收到的是否是滞留消息
            if(csMessage.getMd5().toCharArray()[0] == 'Z'){
                //删除数据库消息
                dStrandedMessage.deleteStrandedMessage(csMessage.getMd5());
            }

            //移除成功发送的消息编号，在验证序列中
            for (int i = 0; i < md5.size(); i++) {
                if (md5.get(i).equals(csMessage.getMd5()))
                    md5.remove(i);
            }

            System.out.println("------------------------------------------------");
            System.out.println("用户" + userId + "收到消息发出回应,回应内容：");
            System.out.println(message);
            System.out.println("------------------------------------------------");
            return csMessageResult.toJsonObject().toString();
        }

        //收到无法处理的系统消息
        System.out.println("------------------------------------------------");
        System.out.println("用户" + userId + "发送了无法处理的系统消息,完整的系统消息：");
        System.out.println(message);
        System.out.println("------------------------------------------------");
        return csMessageResult.toJsonObject().toString();
    }

    //用户给自己发送消息
    private String userSendMessageForMyself(String message,CSMessage csMessageResult){
        System.out.println("------------------------------------------------");
        System.out.println("用户:" + this.userId + "  给自己发送消息,完整的消息:");
        System.out.println(message);
        System.out.println("------------------------------------------------");
        return csMessageResult.toJsonObject().toString();
    }

    //帖子与用户间的对话
    private void userAndPost(CSMessage csMessage){
        String []temp = csMessage.getReId().split(",");
        if(csMessage.getReId().charAt(0) == 'T'){
            if(temp.length==1){
                csMessage.setSendId(temp[0]+","+csMessage.getSendId());
                //查找发帖人
                if(csMessage.getReId().charAt(1) == 'B'){
                    csMessage.setReId(dStrandedMessage.getBuId(temp[0].substring(2,temp[0].length())));
                }else{
                    csMessage.setReId(dStrandedMessage.getGuId(temp[0].substring(2,temp[0].length())));
                }


                System.out.println(Tool.classToJson(csMessage));
            }else{
                csMessage.setSendId(temp[0]);
                csMessage.setReId(temp[1]);

                System.out.println(Tool.classToJson(csMessage));
            }

        }else {
            if(temp.length != 2){
                System.out.println("长度错误temp.length="+temp.length);
            }else {

                csMessage.setSendId(temp[0]+","+csMessage.getSendId());
                csMessage.setReId(temp[1]);

                System.out.println(Tool.classToJson(csMessage));
            }

        }

    }

    //发送消息给离线用户
    private String snedOffUser(CSMessage csMessage,CSMessage csMessageResult){

        if(dStrandedMessage.exUId(csMessage.getReId())){
            return csMessageResult.toJsonObject().toString();
        }


        //存储消息
        StrandedMessage strandedMessage = new StrandedMessage();

        strandedMessage.setU_id1(csMessage.getSendId());
        strandedMessage.setU_id2(csMessage.getReId());
        strandedMessage.setDia_message(csMessage.getMessage());
        strandedMessage.setMd5("Z"+csMessage.getMd5());

        this.dStrandedMessage.setStrandedMessage(strandedMessage);

        String str = "用户" + this.userId.toString() + "发送对象不在线！收信息人ID" + csMessage.getReId() + "不在线，消息被存储在数据库";
        System.out.println("------------------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------------------");
        return csMessageResult.toJsonObject().toString();
    }

    public String getUserId() {
        return userId;
    }

    public static Map<String, List<UserLinkMessage>> getOnLineUsers() {
        return onLineUsers;
    }
}