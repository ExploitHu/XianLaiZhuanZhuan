package biao.community.controller;


import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port2_10and3_9.JsonValue;
import biao.community.information.port2_10and3_9.Result;
import biao.community.service.SGetHistory;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CGetHistory {

    @Autowired
    SGetHistory sGetHistory;

    @Autowired
    Examine examine;

    @RequestMapping("bGetHistory")
    public JSONObject bGetHistory(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/){
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setError("请检查sKey");
        }else {
            //检查u_id
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                result.setError("请检查u_id");
            }
        }

        if(result.getError().length() == 0){
            if(jsonValueClass.getTime().length()==0){
                jsonValueClass.setTime("now");
            }
            List<JSONObject> list = sGetHistory.bGetHistory(jsonValueClass,result);

            result.setHistory(list);
        }

        Information information = new Information();

        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);
    }

    @RequestMapping("gGetHistory")
    public JSONObject gGetHistory(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/){
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setError("请检查sKey");
        }else {
            //检查u_id
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                result.setError("请检查u_id");
            }
        }


        if(result.getError().length() == 0){
            if(jsonValueClass.getTime().length()==0){
                jsonValueClass.setTime("now");
            }
            List<JSONObject> list = sGetHistory.gGetHistory(jsonValueClass,result);

            result.setHistory(list);
        }
        Information information = new Information();

        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);

    }
}
