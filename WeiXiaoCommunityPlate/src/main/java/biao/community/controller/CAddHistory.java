package biao.community.controller;

import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port2_9and3_8.JsonValue;
import biao.community.information.port2_9and3_8.Result;
import biao.community.service.SAddHistory;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CAddHistory {

    @Autowired
    SAddHistory sAddHistory;

    @Autowired
    Examine examine;

    @RequestMapping("bAddHistory")
    public JSONObject bAddHistory(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/){


        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("sKey错误");
        }else {
            //检查u_id
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                result.setState("请检查u_id");
            }

            //检查帖子id
            if(examine.exBtId(Integer.parseInt(jsonValueClass.getBg_id()))){
                result.setState("请检查bg_id");
            }
        }

        if(result.getState().equals("success") ){
            sAddHistory.bAddHistory(jsonValueClass);
        }


        Information information = new Information();

        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);


    }

    @RequestMapping("gAddHistory")
    public JSONObject gAddHistory(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/){
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("sKey错误");
        }else {

            //检查u_id
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                result.setState("请检查u_id");
            }

            //检查帖子id
            if(examine.exGId(Integer.parseInt(jsonValueClass.getBg_id()))){
                result.setState("请检查bg_id");
            }
        }


        if(result.getState().equals("success") ){
            sAddHistory.gAddHistory(jsonValueClass);
        }

        Information information = new Information();

        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);

    }

}
