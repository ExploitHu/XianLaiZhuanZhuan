package biao.community.controller;


import biao.community.dao.DLogIn;
import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port5_1.RegisterMessage;
import biao.community.information.port5_5.LogInMessage;
import biao.community.information.port5_5.Result;
import biao.community.service.SLogIn;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CLogIn {

    @Autowired
    SLogIn  sLogIn;


    @RequestMapping("/login")
    public JSONObject logIn(@RequestHeader("sKey") String sKey, @RequestBody String message/**postJson是json字符串**/){
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(message);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        LogInMessage logInMessage = JSONObject.toJavaObject(jsonObject1, LogInMessage.class);


        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(message),sKey)){
            result.setState("请检查sKey");
        }else {
            String u_id = sLogIn.logIn(logInMessage);
            if(u_id != null){
                result.setState("success");
                result.setU_id(u_id);
            }else {
                result.setState("defeated");
            }
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);
    }

}
