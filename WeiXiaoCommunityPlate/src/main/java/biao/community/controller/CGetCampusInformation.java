package biao.community.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import biao.community.service.SGetCampusInformation;

@CrossOrigin
@RestController
public class CGetCampusInformation {




    @Autowired
    SGetCampusInformation sGetCampusInformation;

    /**
     * 接口5.4获取校区编号表
     * 查询campus表，并返回数据
     * @return* 校区编号表
     * */

    @RequestMapping("/getCampusInformation")
    public JSONObject getInformation(){

        return sGetCampusInformation.getInformation();

    }

}
