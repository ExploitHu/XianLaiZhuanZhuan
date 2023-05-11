package biao.im;

import biao.im.controller.WsServerEndpoint;
import biao.im.dao.DStrandedMessage;
import biao.im.message.CSMessage;
import biao.im.message.StrandedMessage;
import biao.im.time.GetTime;
import biao.im.tool.Tool;
import biao.im.tool.UserLinkMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ProcessMessage {

    //客户端发来的消息
    private CSMessage csMessage;
    //WsServer
    private WsServerEndpoint wsServerEndpoint;
    //时间
    private String time;
    //返回消息
    private String result;

    @Autowired
    DStrandedMessage dStrandedMessage;



    //构造函数
    public ProcessMessage(CSMessage csMessage, WsServerEndpoint wsServerEndpoint){
        this.csMessage = csMessage;
        this.wsServerEndpoint =wsServerEndpoint;
        this.time = GetTime.getWebsiteDatetime("http://www.ntsc.ac.cn") ;// [中国科学院国家授时中心]"
        this.time = GetTime.formattingTime(this.time);

        //result
        CSMessage csMessageResult = new CSMessage();
        csMessageResult.setSystem(true);
        csMessageResult.setSendId(csMessage.getSendId());
        csMessageResult.setMd5(csMessage.getMd5());
        csMessageResult.setSendTime(this.time);
        result = csMessageResult.toJsonObject().toString();
    }

    public String sortingTreatment(){
        if (csMessage.isIsSystem() == true){
            return this.isSystem();
        }else if (csMessage.getReId().equals(wsServerEndpoint.getUserId())) {
            System.out.println("------------------------------------------------");
            System.out.println("用户:" + wsServerEndpoint.getUserId() + "  给自己发送消息,完整的消息:");
            System.out.println(Tool.classToJson(this.csMessage));
            System.out.println("------------------------------------------------");
            return result;
        }else {
            return this.ordinaryMessage();
        }
    }

    //普通用户对用户消息(一般消息)
    public String ordinaryMessage(){
        //用户发送消息给离线用户  存储消息到数据库  用户上线后接收
        List<UserLinkMessage> listULM = WsServerEndpoint.getOnLineUsers().get(csMessage.getReId());
        if (listULM == null) {
            //存储消息
            StrandedMessage strandedMessage = new StrandedMessage();

            strandedMessage.setU_id1(wsServerEndpoint.getUserId().toString());
            strandedMessage.setU_id2(csMessage.getReId());
            strandedMessage.setDia_message(csMessage.getMessage().toString());
            strandedMessage.setMd5("Z"+csMessage.getMd5());

            //this.dStrandedMessage.setStrandedMessage(strandedMessage);


            String str = "用户" + wsServerEndpoint.getUserId().toString() + "发送对象不在线！收信息人ID" + csMessage.getReId() + "不在线，消息被存储在数据库";
            System.out.println("------------------------------------------------");
            System.out.println(str);
            System.out.println("------------------------------------------------");
            return result;
        }


        System.out.println("向用户" + csMessage.getReId() + "发送：" + csMessage.toJsonObject().toString());


        try{
            //对用户发送一般消息
            wsServerEndpoint.sendMessage(listULM,csMessage.toJsonObject().toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    //帖子消息（用户对帖子，帖子发布者对用户）
    public String isPost(){
        return "";
    }


    //系统消息
    public String isSystem(){
        //心跳连接回复
        if (csMessage.getMessage().equals("心跳连接")) {
            CSMessage tempCsMessage = new CSMessage(this.time,"心跳回复");
            String temp = tempCsMessage.toJsonObject().toString();
            return temp;
        }

        //客户端收到消息发出回应
        if (csMessage.getMessage().equals("respond")) {

            if(csMessage.getMd5().toCharArray()[0] == 'Z'){
                //删除数据库消息
                dStrandedMessage.deleteStrandedMessage(csMessage.getMd5());
            }

            //移除成功发送的消息编号，在验证序列中
            for (int i = 0; i < WsServerEndpoint.md5.size(); i++) {
                if (WsServerEndpoint.md5.get(i).equals(csMessage.getMd5()))
                    WsServerEndpoint.md5.remove(i);
            }

            System.out.println("------------------------------------------------");
            System.out.println("用户" + wsServerEndpoint.getUserId() + "收到消息发出回应,回应内容：");
            System.out.println(Tool.classToJson(csMessage));
            System.out.println("------------------------------------------------");
            return result;
        }

        //收到无法处理的系统消息
        System.out.println("------------------------------------------------");
        System.out.println("用户" + wsServerEndpoint.getUserId() + "发送了无法处理的系统消息,完整的系统消息：");
        System.out.println(Tool.classToJson(csMessage));
        System.out.println("------------------------------------------------");
        return result;
    }




}
