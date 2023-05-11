package biao.community.service;

import biao.community.information.port2_14and3_14.AttCDInformation;
import biao.community.information.port2_14and3_14.AttGInformation;
import biao.community.information.port2_14and3_14.JsonValue;
import biao.community.information.port2_1and3_1.GoodPost;
import biao.community.time.GetTime;
import biao.community.tool.Tool;
import biao.community.dao.DGetPost;
import biao.community.information.Time;
import biao.community.information.port2_1and3_1.CommunityPostInformation;
import biao.community.information.port2_1and3_1.Result;
import biao.community.information.port2_1and3_1.UserInformation5;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SGetPost {

    @Autowired
    DGetPost dGetCommunityDynamic;

    public List<JSONObject> getCommunityDynamic(biao.community.information.port2_1and3_1.JsonValue jsonValueClass, Result result) {
        List<JSONObject> resultJsonList = new ArrayList<JSONObject>();
        List<CommunityPostInformation> list;

        if (jsonValueClass.getBs_id().length() != 0) {
            jsonValueClass.setBs_id(dGetCommunityDynamic.getBsIdName(Integer.parseInt(jsonValueClass.getBs_id())));
        } else {
            jsonValueClass.setBs_id("%%");
        }

        if (jsonValueClass.getU_city().length() == 0) {
            jsonValueClass.setU_city("%%");
        }


        list = dGetCommunityDynamic.getCommunityDynamic(jsonValueClass);


        if(list.size()!=0){
            result.setSlm(list.get(list.size()-1).getRecommendValue()+","+list.get(list.size()-1).getBt_time_Temp());
        }

        for (int i = 0; i < list.size(); i++) {

            //处理图片
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
            if(list.get(i).getB_anonymous()){
                //匿名
                userBriefInformation = dGetCommunityDynamic.getAnonUserInformation(Integer.parseInt(list.get(i).getU_id()));
                list.get(i).setUser(Tool.classToJson(userBriefInformation));
            }else{
                //不匿名
                userBriefInformation = dGetCommunityDynamic.getUserBriefInformation(Integer.parseInt(list.get(i).getU_id()));
                list.get(i).setUser(Tool.classToJson(userBriefInformation));
                //处理isBooked
                list.get(i).setIsBooked(dGetCommunityDynamic.judgeBooked(Integer.parseInt(jsonValueClass.getU_id()), Integer.parseInt(list.get(i).getU_id())));
            }

            //处理isLiked
            list.get(i).setIsLike(
                    dGetCommunityDynamic.judgeLike(
                            Integer.parseInt(jsonValueClass.getU_id()),Integer.parseInt(list.get(i).getBt_id())
                    )
            );

            //处理isCollect
            list.get(i).setCollect(!dGetCommunityDynamic.isBCollect(list.get(i).getBt_id(),jsonValueClass.getU_id()));

            //将对象转为json为输出做准备
            resultJsonList.add(Tool.classToJson(list.get(i)));
            //删除多余的键
            //删除"bt_time_Temp"&"u_id"
            resultJsonList.get(i).remove("bt_time_Temp");
            resultJsonList.get(i).remove("u_id");
        }


        return resultJsonList;

    }

    public List<JSONObject> getGoodDetail(biao.community.information.port2_1and3_1.JsonValue jsonValueClass, Result result) {
        List<JSONObject> resultJsonList = new ArrayList<JSONObject>();
        List<GoodPost> list;

        if (jsonValueClass.getG_class().length() != 0) {
            jsonValueClass.setG_class(dGetCommunityDynamic.getGclassName(Integer.parseInt(jsonValueClass.getG_class())));
        } else {
            jsonValueClass.setG_class("%%");
        }
        if (jsonValueClass.getU_city().length() == 0) {
            jsonValueClass.setU_city("%%");
        }
        list = dGetCommunityDynamic.getGoodDetail(jsonValueClass);

        if(list.size()!=0){
            result.setSlm(list.get(list.size()-1).getRecommendValue()+","+list.get(list.size()-1).getG_time());
        }

        for (GoodPost goodPost: list){

            List<String> listImage;
            if(goodPost.getG_image() != null){
                listImage = Arrays.asList(goodPost.getG_image().toString().split(","));
            }else{
                listImage = new ArrayList<>();
            }

            //处理点赞
            goodPost.setLike(dGetCommunityDynamic.isGlike(goodPost.getG_id(),jsonValueClass.getU_id()));

            //处理匿名
            UserInformation5 userBriefInformation;
            //处理user
            if(goodPost.isG_anonymous()){
                //匿名
                userBriefInformation = dGetCommunityDynamic.getAnonUserInformation(Integer.parseInt(goodPost.getU_id()));
                goodPost.setUser(Tool.classToJson(userBriefInformation));
            }else{
                //不匿名
                userBriefInformation = dGetCommunityDynamic.getUserBriefInformation(Integer.parseInt(goodPost.getU_id()));
                goodPost.setUser(Tool.classToJson(userBriefInformation));
                //处理isBooked
                goodPost.setIsBooked(dGetCommunityDynamic.judgeBooked(Integer.parseInt(jsonValueClass.getU_id()), Integer.parseInt(goodPost.getU_id())));
            }

            //处理isCollect
            goodPost.setCollect(!dGetCommunityDynamic.isGCollect(goodPost.getG_id(),jsonValueClass.getU_id()));


            goodPost.setG_image(listImage);
            resultJsonList.add(Tool.classToJson(goodPost));
            resultJsonList.get(resultJsonList.size()-1).remove("u_id");
        }


        return resultJsonList;

    }

    //功能：根据id获取用户时间time之前发布的sum个社区帖子
    public List<JSONObject> getAttCommunityDynamic(JsonValue jsonValueClass, biao.community.information.port2_13and3_12.Result result){
        List<AttCDInformation> list = dGetCommunityDynamic.getAttCommunityDynamic(jsonValueClass);
        List<JSONObject> jsonList = new ArrayList<>();

        if(list.size()!=0){
            result.setTime(GetTime.antonymFormatTime(list.get(list.size()-1).getBt_time()));
        }

        for (int i = 0; i < list.size(); i++){

            List<String> listImage;
            if(list.get(i).getBt_image() != null){
                listImage = Arrays.asList(list.get(i).getBt_image().toString().split(","));
            }else{
                listImage = new ArrayList<>();
            }
            list.get(i).setBt_image(listImage);


            Time time = new Time(list.get(i).getBt_time());
            JSONObject timeJson = Tool.classToJson(time);

            UserInformation5 userBriefInformation;
            userBriefInformation = dGetCommunityDynamic.getUserBriefInformation((int)list.get(i).getUser());

            //处理isCollect
            list.get(i).setCollect(!dGetCommunityDynamic.isBCollect(list.get(i).getBt_id(),list.get(i).getUser().toString()));

            list.get(i).setUser(Tool.classToJson(userBriefInformation));

            jsonList.add(Tool.classToJson(list.get(i)));

            jsonList.get(i).remove("bt_time");
            jsonList.get(i).put("bt_time",timeJson);

        }

        return jsonList;
    }

    //功能：根据id获取用户时间time之前发布的sum个社区帖子
    public List<JSONObject> getAttGPost(JsonValue jsonValueClass, biao.community.information.port2_13and3_12.Result result){
        List<AttGInformation> list = dGetCommunityDynamic.getAttGPost(jsonValueClass);
        List<JSONObject> jsonList = new ArrayList<>();

        if(list.size()!=0){
            result.setTime(GetTime.antonymFormatTime(list.get(list.size()-1).getG_time()));
        }

        for (int i = 0; i < list.size(); i++){

            List<String> listImage;
            if(list.get(i).getG_image() != null){
                listImage = Arrays.asList(list.get(i).getG_image().toString().split(","));
            }else{
                listImage = new ArrayList<>();
            }
            list.get(i).setG_image(listImage);


            Time time = new Time(list.get(i).getG_time());
            JSONObject timeJson = Tool.classToJson(time);

            UserInformation5 userBriefInformation;
            userBriefInformation = dGetCommunityDynamic.getUserBriefInformation((int)list.get(i).getUser());

            //处理isCollect
            list.get(i).setCollect(!dGetCommunityDynamic.isBCollect(list.get(i).getG_id(),list.get(i).getUser().toString()));

            list.get(i).setUser(Tool.classToJson(userBriefInformation));

            jsonList.add(Tool.classToJson(list.get(i)));

            jsonList.get(i).remove("g_time");
            jsonList.get(i).put("g_time",timeJson);

        }

        return jsonList;
    }

}
