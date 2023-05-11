package biao.community.service;

import biao.community.dao.DGetCampusInformation;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import biao.community.information.Information;
import biao.community.information.port5_4.CampusInformation;
import biao.community.information.port5_4.Result;
import biao.community.tool.Tool;

import java.util.ArrayList;
import java.util.List;

@Service
public class SGetCampusInformation {

    @Autowired
    DGetCampusInformation dGetCampusInformation;

    public JSONObject getInformation() {

        List<CampusInformation> campusInformation = dGetCampusInformation.getInformation();

        List<JSONObject> list = new ArrayList<JSONObject>();

        for (int i = 0; i < campusInformation.size(); i++){
            list.add(Tool.classToJson(campusInformation.get(i)));
        }

        Result result=new Result();
        result.setCampus(list);
        result.setCode(200);

        Information information=new Information();
        information.setData(Tool.classToJson(result));
        return Tool.classToJson(information);

    }

}
