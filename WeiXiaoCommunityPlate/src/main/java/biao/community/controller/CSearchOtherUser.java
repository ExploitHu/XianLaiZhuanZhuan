package biao.community.controller;


import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port4_3.JsonValue;
import biao.community.information.port4_3.Result;
import biao.community.service.SSearchOtherUser;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CSearchOtherUser {

    @Autowired
    Examine examine;

    @Autowired
    SSearchOtherUser sSearchOtherUser;

    @RequestMapping("searchOtherUser")
    public JSONObject searchOtherUser(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/) {
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("请检查sKey");
        }else {

            //检查u_id与searchUser
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                result.setState("请检查u_id");
            }
            if (examine.exUId(Integer.parseInt(jsonValueClass.getSearchUser()))){
                result.setState("请检查searchUser");
            }
        }


        if(result.getState().equals("success")){
            result.setInformation(sSearchOtherUser.searchOtherUser(jsonValueClass));
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));
        return Tool.classToJson(information);
    }

}
