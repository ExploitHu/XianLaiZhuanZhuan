package biao.community.service;


import biao.community.dao.DGetPost;
import biao.community.dao.DSearchKeywords;
import biao.community.information.Time;
import biao.community.information.port2_1and3_1.CommunityPostInformation;
import biao.community.information.port2_1and3_1.Result;
import biao.community.information.port2_1and3_1.UserInformation5;
import biao.community.information.port2_7.JsonValue;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SSearchKeywords {

    @Autowired
    DSearchKeywords dSearchKeywords;

    @Autowired
    DGetPost dGetCommunityDynamic;

    public void searchKeywords(JsonValue jsonValueClass, Result result){
        List<JSONObject> resultJsonList = new ArrayList<JSONObject>();
        List<CommunityPostInformation> list;




        //处理slm
        if(jsonValueClass.getSlm()==null){
            jsonValueClass.setRecommendValue(146);
            jsonValueClass.setTime("now");
        }else if (jsonValueClass.getSlm().length()==0){
            jsonValueClass.setRecommendValue(146);
            jsonValueClass.setTime("now");
        }else {
            List<String> listSlm = Arrays.asList(jsonValueClass.getSlm().split(","));
            //设置时间  推荐值
            jsonValueClass.setRecommendValue(Float.parseFloat(listSlm.get(0)));
            jsonValueClass.setTime(listSlm.get(1));
        }

        list = dSearchKeywords.searchKeywordsGetSize(jsonValueClass);

        for (int i = 0; i < list.size(); i++) {
            List<String> listImage;
            if(list.get(i).getBt_image() == null){
                listImage = new ArrayList<>();
            }else{
                listImage = Arrays.asList(list.get(i).getBt_image().toString().split(","));
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
            }

            //处理isLiked
            list.get(i).setIsLike(dGetCommunityDynamic.judgeLike(Integer.parseInt(jsonValueClass.getU_id()),Integer.parseInt(list.get(i).getBt_id())));

            //处理isBooked
            list.get(i).setIsBooked(dGetCommunityDynamic.judgeBooked(Integer.parseInt(jsonValueClass.getU_id()), Integer.parseInt(list.get(i).getU_id())));


            //isCollect
            list.get(i).setCollect(!dGetCommunityDynamic.isBCollect(list.get(i).getBt_id(),list.get(i).getU_id()));

            //将对象转为json为输出做准备
            resultJsonList.add(Tool.classToJson(list.get(i)));
            //删除多余的键
            //删除"bt_time_Temp"&"u_id"
            resultJsonList.get(i).remove("bt_time_Temp");
            resultJsonList.get(i).remove("u_id");
        }

        if(list.size()!=0){
            result.setSlm(Float.toString(list.get(list.size()-1).getRecommendValue())+","+list.get(list.size()-1).getBt_time_Temp());
        }

        result.setPost(resultJsonList);




    }

}
