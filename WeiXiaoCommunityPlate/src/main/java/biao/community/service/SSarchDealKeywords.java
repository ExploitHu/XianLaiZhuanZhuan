package biao.community.service;

import biao.community.dao.DGetPost;
import biao.community.dao.DSarchDealKeywords;
import biao.community.information.port2_1and3_1.UserInformation5;
import biao.community.information.port3_6.DealInformation;
import biao.community.information.port3_6.JsonValue;
import biao.community.information.port3_6.Result;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SSarchDealKeywords {

    @Autowired
    DSarchDealKeywords dSarchDealKeywords;

    @Autowired
    DGetPost dGetPost;

    public void sarchDealKeywords(JsonValue jsonValueClass, Result result){

        List<DealInformation> list ;

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


        list = dSarchDealKeywords.searchKeywordsGetSize(jsonValueClass);

        List<JSONObject> listJson = new ArrayList<JSONObject>();



        for (int i = 0 ; i < list.size(); i++){

            List<String> imageList;
            if(list.get(i).getG_image() == null){
                imageList = new ArrayList<String>();
            }else{
                imageList =  Arrays.asList(list.get(i).getG_image().toString().split(","));
            }

            list.get(i).setG_image(imageList);

            UserInformation5 userInformation5;
            if(list.get(i).isG_anonymous()){
                //匿名
                userInformation5 = dGetPost.getAnonUserInformation(Integer.parseInt(list.get(i).getU_id()));
                list.get(i).setUser(Tool.classToJson(userInformation5));
            }else{
                //不匿名
                userInformation5 = dGetPost.getUserBriefInformation(Integer.parseInt(list.get(i).getU_id()));
                list.get(i).setUser(Tool.classToJson(userInformation5));
            }

            //isCollect
            list.get(i).setCollect(!dSarchDealKeywords.isGCollect(list.get(i).getG_id(),list.get(i).getU_id()));

            listJson.add(Tool.classToJson(list.get(i)));
        }
        if(list.size()!=0){
            result.setSlm(Float.toString(list.get(list.size()-1).getRecommendValue())+","+list.get(list.size()-1).getG_time());
        }
        result.setGoodList(listJson);
        //return listJson;
    }

}
