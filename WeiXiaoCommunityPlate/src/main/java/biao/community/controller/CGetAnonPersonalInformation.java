package biao.community.controller;

import biao.community.information.Information;
import biao.community.information.port1_2.AnonPersonalId;
import biao.community.information.port5_3.VerificationCode;
import biao.community.service.SGetAnonPersonalInformation;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import biao.community.service.SGetPersonalInformation;

@CrossOrigin
@RestController
public class CGetAnonPersonalInformation {

    @Autowired
    SGetAnonPersonalInformation sGetAnonPersonalInformation;


    @RequestMapping("getAnonPersonalInformation")
    public JSONObject getAnonPersonalInformation(@RequestHeader("sKey") String sKey,@RequestBody String message/**postJson是json字符串**/) {
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(message);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        AnonPersonalId anonPersonalId = JSONObject.toJavaObject(jsonObject1, AnonPersonalId.class);

        Information information = new Information();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(message),sKey)){
            information.setData("sKey错误");
        }else {
            JSONObject anonPersonalInformationJson = sGetAnonPersonalInformation.getAnonPersonalInformation(anonPersonalId);
            information.setData(anonPersonalInformationJson);
        }

        return Tool.classToJson(information);
    }
}
