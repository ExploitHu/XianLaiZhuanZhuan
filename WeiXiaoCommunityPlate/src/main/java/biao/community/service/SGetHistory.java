package biao.community.service;

import biao.community.dao.DGetHistory;
import biao.community.information.Time;
import biao.community.information.port2_10and3_9.BHistory;
import biao.community.information.port2_10and3_9.GHistory;
import biao.community.information.port2_10and3_9.JsonValue;
import biao.community.time.GetTime;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import biao.community.information.port2_10and3_9.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SGetHistory {

    @Autowired
    DGetHistory dGetHistory;

    public List<JSONObject> bGetHistory(JsonValue jsonValueClass,Result result){

        List<BHistory> list = dGetHistory.bGetHistory(jsonValueClass);
        if(list.size()!=0){
            result.setTime(list.get(list.size()-1).getTime());
        }

        List<JSONObject> listJson = new ArrayList<JSONObject>();

        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getBt_image()!=null){
                List<String> listImage = Arrays.asList(list.get(i).getBt_image().toString().split(","));
                list.get(i).setBt_image(listImage.get(0));
            }



            list.get(i).setTime(GetTime.antonymFormatTime(list.get(i).getTime()));

            listJson.add(Tool.classToJson(list.get(i)));
        }

        return listJson;
    }


    public List<JSONObject> gGetHistory(JsonValue jsonValueClass,Result result){
        List<GHistory> list = dGetHistory.gGetHistory(jsonValueClass);
        if(list.size()!=0){
            result.setTime(list.get(list.size()-1).getTime());
        }

        List<JSONObject> listJson = new ArrayList<JSONObject>();

        for (int i = 0; i < list.size(); i++){

            if(list.get(i).getG_image()!=null){
                List<String> listImage = Arrays.asList(list.get(i).getG_image().toString().split(","));
                list.get(i).setG_image(listImage.get(0));
            }

            list.get(i).setTime(GetTime.antonymFormatTime(list.get(i).getTime()));

            listJson.add(Tool.classToJson(list.get(i)));
        }

        return listJson;
    }

}
