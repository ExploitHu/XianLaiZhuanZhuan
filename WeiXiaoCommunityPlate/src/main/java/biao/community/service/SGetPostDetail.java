package biao.community.service;

import biao.community.dao.DGetPost;
import biao.community.dao.DGetPostDetail;
import biao.community.information.Time;
import biao.community.information.port2_1and3_1.CommunityPostInformation;
import biao.community.information.port2_1and3_1.UserInformation5;
import biao.community.information.port3_2.GDInformation;
import biao.community.information.port3_2.JsonValue;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SGetPostDetail {

    @Autowired
    DGetPostDetail dGetPostDetail;

    @Autowired
    DGetPost dGetPost;


    public JSONObject getBPostDetail(JsonValue jsonValueClass){


        CommunityPostInformation cPI = dGetPostDetail.getBPostetail(jsonValueClass.getBt_id());

        //处理图片
        List<String> listImage;
        if(cPI.getBt_image() != null){
            listImage = Arrays.asList(cPI.getBt_image().toString().split(","));
        }else{
            listImage = new ArrayList<>();
        }
        cPI.setBt_image(listImage);

        //处理bt_time
        Time time = new Time(cPI.getBt_time_Temp());
        cPI.setBt_time(Tool.classToJson(time));

        UserInformation5 userBriefInformation;
        //处理user
        if(cPI.getB_anonymous()){
            //匿名
            userBriefInformation = dGetPost.getAnonUserInformation(Integer.parseInt(cPI.getU_id()));
            cPI.setUser(Tool.classToJson(userBriefInformation));
        }else{
            //不匿名
            userBriefInformation = dGetPost.getUserBriefInformation(Integer.parseInt(cPI.getU_id()));
            cPI.setUser(Tool.classToJson(userBriefInformation));
            //处理isBooked
            if(jsonValueClass.getU_id().equals("0")){
                cPI.setIsBooked(false);
            }else{
                cPI.setIsBooked(dGetPost.judgeBooked(Integer.parseInt(jsonValueClass.getU_id()), Integer.parseInt(cPI.getU_id())));
            }
        }

        //处理isLiked
        if(jsonValueClass.equals("0")){
            cPI.setIsLike(false);
        }else{
            cPI.setIsLike(dGetPost.judgeLike(Integer.parseInt(jsonValueClass.getU_id()),Integer.parseInt(cPI.getBt_id())));
        }

        //处理isCollect
        if(jsonValueClass.equals("0")){
            cPI.setCollect(false);
        }else{
            cPI.setCollect(!dGetPost.isBCollect(cPI.getBt_id(),jsonValueClass.getU_id()));
        }

        JSONObject resultJson = Tool.classToJson(cPI);

        //删除多余的键
        //删除"bt_time_Temp"&"u_id"
        resultJson.remove("bt_time_Temp");
        resultJson.remove("u_id");

        return resultJson;
    }


    public JSONObject getGPostDetail(JsonValue jsonValueClass){

        GDInformation gdInformation = dGetPostDetail.getGPostetail(jsonValueClass.getG_id());

        //处理图片格式
        List<String> imageList;
        if(gdInformation.getG_image()!=null){
            if(gdInformation.getG_image().toString().length()!=0){
                imageList = Arrays.asList(gdInformation.getG_image().toString().split(","));
            }else{
                imageList = new ArrayList<>();
            }
        }else{
            imageList = new ArrayList<>();
        }
        gdInformation.setG_image(imageList);

        //查询用户是否点赞
        if(jsonValueClass.equals("0")){
            gdInformation.setLike(false);
        }else{
            gdInformation.setLike(dGetPostDetail.isLike(jsonValueClass.getG_id(),jsonValueClass.getU_id()));
        }


        //查询是否收藏
        if(jsonValueClass.equals("0")){
            gdInformation.setCollect(false);
        }else{
            gdInformation.setCollect(!dGetPostDetail.isGCollect(gdInformation.getG_id(),jsonValueClass.getU_id()));
        }


        UserInformation5 userBriefInformation;
        //处理user
        if(gdInformation.isG_anonymous()){
            //匿名
            userBriefInformation = dGetPost.getAnonUserInformation(Integer.parseInt(gdInformation.getU_id()));
            gdInformation.setUser(Tool.classToJson(userBriefInformation));
        }else{
            //不匿名
            userBriefInformation = dGetPost.getUserBriefInformation(Integer.parseInt(gdInformation.getU_id()));
            gdInformation.setUser(Tool.classToJson(userBriefInformation));
            //处理isBooked
            if(jsonValueClass.equals("0")){
                gdInformation.setBooked(false);

            }else{
                gdInformation.setBooked(dGetPost.judgeBooked(Integer.parseInt(jsonValueClass.getU_id()), Integer.parseInt(gdInformation.getU_id())));

            }
        }

        //处理时间
        Time time = new Time(gdInformation.getG_time());
        JSONObject jsonObject = Tool.classToJson(gdInformation);
        jsonObject.remove("g_time");
        jsonObject.remove("u_id");
        jsonObject.put("g_time",Tool.classToJson(time));

        return jsonObject;
    }

}
