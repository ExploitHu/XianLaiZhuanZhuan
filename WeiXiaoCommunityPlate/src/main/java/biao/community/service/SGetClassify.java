package biao.community.service;

import biao.community.dao.DGetClassify;
import biao.community.information.Information;
import biao.community.information.port2_8and3_7.BClassify;
import biao.community.information.port2_8and3_7.GClassify;
import biao.community.information.port2_8and3_7.Result;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SGetClassify {

    @Autowired
    DGetClassify dGetClassify;

    public JSONObject getBClassify(){

       List<BClassify> list = dGetClassify.getBClassify();
       List<JSONObject> listJson = new ArrayList<JSONObject>();
       for (int i = 0; i< list.size(); i++){
           listJson.add(Tool.classToJson(list.get(i)));
       }

       Result result = new Result();
       result.setClassify(listJson);

       return Tool.classToJson(result);
    }


    public JSONObject getGClassify(){
        List<GClassify> list = dGetClassify.getGClassify();
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        for (int i = 0; i< list.size(); i++){
            listJson.add(Tool.classToJson(list.get(i)));
        }

        Result result = new Result();
        result.setClassify(listJson);

        return Tool.classToJson(result);
    }
}
