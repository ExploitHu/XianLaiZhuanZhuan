package biao.community.service;

import biao.community.dao.DGetCollect;
import biao.community.information.port2_11and3_10.BCollect;
import biao.community.information.port2_11and3_10.GCollect;
import biao.community.information.port2_11and3_10.JsonValue;
import biao.community.time.GetTime;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SGetCollect {

    @Autowired
    DGetCollect dGetCollect;

    public List<JSONObject> bGetCollect(JsonValue jsonValueClass){
        List<BCollect> list = dGetCollect.bgetCollect(Integer.parseInt(jsonValueClass.getU_id()));

        List<JSONObject> listJson = new ArrayList<JSONObject>();

        for (int i = 0;i < list.size(); i++){
            //修改时间格式
            list.get(i).setTime(GetTime.antonymFormatTime(list.get(i).getTime()));

            listJson.add(Tool.classToJson(list.get(i)));
        }


        return listJson;

    }

    public List<JSONObject> gGetCollect(JsonValue jsonValueClass){

        List<GCollect> list = dGetCollect.ggetCollect(Integer.parseInt(jsonValueClass.getU_id()));

        List<JSONObject> listJson = new ArrayList<JSONObject>();

        for (int i = 0;i < list.size(); i++){
            //修改时间格式
            list.get(i).setTime(GetTime.antonymFormatTime(list.get(i).getTime()));

            listJson.add(Tool.classToJson(list.get(i)));
        }


        return listJson;


    }

}
