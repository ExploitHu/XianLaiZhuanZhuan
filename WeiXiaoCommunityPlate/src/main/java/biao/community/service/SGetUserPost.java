package biao.community.service;


import biao.community.dao.DGetPost;
import biao.community.dao.DGetUserPost;
import biao.community.information.Time;
import biao.community.information.port2_1and3_1.CommunityPostInformation;
import biao.community.information.port2_1and3_1.UserInformation5;
import biao.community.information.port2_13and3_12.DealInformation;
import biao.community.information.port2_13and3_12.JsonValue;
import biao.community.time.GetTime;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import biao.community.information.port2_13and3_12.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class SGetUserPost {

    @Autowired
    DGetUserPost dGetUserPost;

    @Autowired
    DGetPost dGetCommunityDynamic;

    public List<JSONObject> getUserBPost(JsonValue jsonValueClass,Result result) {

        List<CommunityPostInformation> list = dGetUserPost.getUserBPost(jsonValueClass);

        List<JSONObject> resultJsonList = new ArrayList<JSONObject>();

        if(list.size()!=0){
            result.setTime(GetTime.antonymFormatTime(list.get(list.size()-1).getBt_time_Temp()));
        }


        for (int i = 0; i < list.size(); i++) {
            List<String> listImage;
            if(list.get(i).getBt_image() != null){
                listImage = Arrays.asList(list.get(i).getBt_image().toString().split(","));
            }else{
                listImage = new ArrayList<>();
            }


            list.get(i).setBt_image(listImage);

            //处理bt_time
            Time time = new Time(list.get(i).getBt_time_Temp());
            list.get(i).setBt_time(Tool.classToJson(time));

            UserInformation5 userBriefInformation;
            //处理user
            if(list.get(i).getB_anonymous()&&!jsonValueClass.getU_id().equals(jsonValueClass.getAim_u_id())){
                list.remove(i);
                i--;
                continue;
            }

            userBriefInformation = dGetCommunityDynamic.getUserBriefInformation(Integer.parseInt(list.get(i).getU_id()));
            list.get(i).setUser(Tool.classToJson(userBriefInformation));

            //处理isLiked
            list.get(i).setIsLike(dGetCommunityDynamic.judgeLike(Integer.parseInt(jsonValueClass.getU_id()),Integer.parseInt(list.get(i).getBt_id())));

            //处理isBooked
            list.get(i).setIsBooked(dGetCommunityDynamic.judgeBooked(Integer.parseInt(jsonValueClass.getU_id()), Integer.parseInt(list.get(i).getU_id())));


            //处理isCollect
            list.get(i).setCollect(!dGetUserPost.isBCollect(list.get(i).getBt_id(),jsonValueClass.getU_id()));

            //将对象转为json为输出做准备
            resultJsonList.add(Tool.classToJson(list.get(i)));
            //删除多余的键
            //删除"bt_time_Temp"&"u_id"
            resultJsonList.get(i).remove("bt_time_Temp");
            resultJsonList.get(i).remove("u_id");
        }

        return resultJsonList;
    }

    public List<JSONObject> getUserGPost(JsonValue jsonValueClass,Result result) {

        List<DealInformation> list = dGetUserPost.getUserGPost(jsonValueClass);

        List<JSONObject> resultJsonList = new ArrayList<JSONObject>();


        if(list.size()!=0){
            result.setTime(GetTime.antonymFormatTime(list.get(list.size()-1).getG_time()));
        }

        for (int i = 0; i < list.size(); i++) {
            List<String> listImage;
            if(list.get(i).getG_image() != null){
                listImage = Arrays.asList(list.get(i).getG_image().toString().split(","));
            }else{
                listImage = new ArrayList<>();
            }

            list.get(i).setG_image(listImage);


            UserInformation5 userBriefInformation;

            //处理user
            if(list.get(i).isG_anonymous()&&!jsonValueClass.getU_id().equals(jsonValueClass.getAim_u_id())){
                list.remove(i);
                i--;
                continue;
            }
            //不匿名
            userBriefInformation = dGetCommunityDynamic.getUserBriefInformation(Integer.parseInt(list.get(i).getU_id()));
            list.get(i).setUser(Tool.classToJson(userBriefInformation));

            //isLIke
            list.get(i).setLike(dGetCommunityDynamic.isGlike(list.get(i).getG_id(),jsonValueClass.getU_id()));

            //isCollect
            list.get(i).setCollect(!dGetUserPost.isGCollect(list.get(i).getG_id(),jsonValueClass.getU_id()));


            //将对象转为json为输出做准备
            resultJsonList.add(Tool.classToJson(list.get(i)));

            resultJsonList.get(i).remove("g_time");
            //删除多余的键
            //删除"bt_time_Temp"&"u_id"
            //resultJsonList.get(i).remove("bt_time_Temp");
            resultJsonList.get(i).remove("u_id");
        }


        return resultJsonList;
    }

}
