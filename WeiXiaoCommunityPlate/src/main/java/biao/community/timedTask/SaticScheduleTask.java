package biao.community.timedTask;

import biao.community.dao.DSaticScheduleTask;
import biao.community.time.GetTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.*;


@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

    private static float isLike = 0.97f;

    private static float isClick = 0.51f;


    @Autowired
    DSaticScheduleTask dSaticScheduleTask;
    //每小时更新一次
    @Scheduled(cron = "0 0 0/1 * * ?")
    //10分钟更新一次
    //@Scheduled(cron = "0 0/10 * * * ?")
    //30秒更新一次
    //@Scheduled(cron = "0/30 * * * * ?")
    public void toDetectTheRetransmission() {

        String now = GetTime.getWebsiteDatetime("https://baidu.com");
        System.out.println("定时任务开始" + now);

        //更新社区值
        this.updateB(now);
        //更新社区值
        this.updateG(now);

        now = GetTime.getWebsiteDatetime("https://baidu.com");
        System.out.println("定时任务结束" + now);

    }

    //更新社区推荐值
    private void updateB(String now){

        List<BInformation> list = dSaticScheduleTask.getBInformation(1000);

        //计算推荐值
        for (BInformation temp:list){
            temp.setRecommendValue(this.calculateRecommendedValue(temp.getBt_time(),now,temp.getBt_like(),temp.getBt_click()));
        }

        //更改数据库中推荐值
        dSaticScheduleTask.setBRecommendedValue(list);

        this.recordB("/root/hubiao/log/SaticScheduleTaskB.txt",list,now);


    }

    //更新交易推荐值
    private void updateG(String now){

        List<GInformation> list = dSaticScheduleTask.getGInformation(1000);

        //计算推荐值
        for(GInformation temp: list){
            temp.setRecommendValue(this.calculateRecommendedValue(temp.getG_time(),now,temp.getG_like(),temp.getG_view()));
        }

        //更改数据库中的推荐值
        dSaticScheduleTask.setGRecommendedValue(list);

        this.recordG("/root/hubiao/log/SaticScheduleTaskG.txt",list,now);

    }


    /***
     * 计算优先值
     * @param time          时间      格式（yyyy-MM-dd HH:mm:ss）
     * @param isLike        点赞量
     * @param isClick       点击量
     * @return
     */
    private float calculateRecommendedValue(String time,String now,int isLike, int isClick){



        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        float recommendValue = 0;

        try{
            Date date1 = format.parse(now);
            Date date2 = format.parse(time);

            recommendValue = 144 - (float) ((date1.getTime() - date2.getTime()) * (1000)) * 2 / 3600 ;

        }catch (Exception exception){
            System.out.println("时间格式转换错误");
            return 0;
        }

        //System.out.println(recommendValue);

        recommendValue += isLike * SaticScheduleTask.isLike;
        recommendValue += isClick * SaticScheduleTask.isClick;

        //System.out.println(recommendValue);

        if(recommendValue > 144) {
            recommendValue = 145;
        }

        if(recommendValue < -500) {
            recommendValue = -500;
        }

        return recommendValue;
    }

    //记录日志B
    private void recordB(String filePath ,List<BInformation> listBInformation,String time){

        //写入的文件的内容
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        //添加写入文件的内容：1000个Map集合型数据
        for(int i = 1;i <= 1000;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("key_"+i, "value_"+i);
            list.add(map);
        }

        try{
            File file = new File(filePath);
            FileOutputStream fos = null;
            if(!file.exists()){
                file.createNewFile();//如果文件不存在，就创建该文件
                fos = new FileOutputStream(file);//首次写入获取
            }else{
                //如果文件已存在，那么就在文件末尾追加写入
                fos = new FileOutputStream(file,true);//这里构造方法多了一个参数true,表示在文件末尾追加写入
            }

            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");//指定以UTF-8格式写入文件

            osw.write("Time:"+time + "update:" + listBInformation.size());
            osw.write("\r\n");
            for (BInformation tempList:listBInformation){
                osw.write("bt_id:" + tempList.getBt_id()+"\t\tRecommendValue:"+tempList.getRecommendValue());
                osw.write("\r\n");
            }


            osw.write("\r\n\n");

            //写入完成关闭流
            osw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //记录日志G
    private void recordG(String filePath ,List<GInformation> listBInformation,String time){

        //写入的文件的内容
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        //添加写入文件的内容：1000个Map集合型数据
        for(int i = 1;i <= 1000;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("key_"+i, "value_"+i);
            list.add(map);
        }

        try{
            File file = new File(filePath);
            FileOutputStream fos = null;
            if(!file.exists()){
                file.createNewFile();//如果文件不存在，就创建该文件
                fos = new FileOutputStream(file);//首次写入获取
            }else{
                //如果文件已存在，那么就在文件末尾追加写入
                fos = new FileOutputStream(file,true);//这里构造方法多了一个参数true,表示在文件末尾追加写入
            }

            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");//指定以UTF-8格式写入文件

            osw.write("Time:"+time + "update:" + listBInformation.size());
            osw.write("\r\n");
            for (GInformation tempList:listBInformation){
                osw.write("g_id:" + tempList.getG_id()+"\t\tRecommendValue:"+tempList.getRecommendValue());
                osw.write("\r\n");
            }


            osw.write("\r\n\n");

            //写入完成关闭流
            osw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
