package biao.community.controller;



import biao.community.dao.Examine;
import biao.community.information.port2_11and3_10.JsonValue;
import biao.community.information.port2_11and3_10.Result;
import biao.community.service.SGetCollect;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CGetCollect {

    @Autowired
    SGetCollect sGetCollect;

    @Autowired
    Examine examine;

    @RequestMapping("bGetCollect")
    public JSONObject bGetCollect(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/) {
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
            List<JSONObject> list = sGetCollect.bGetCollect(jsonValueClass);

            result.setCollect(list);
        }


        return Tool.classToJson(result);
    }

    @RequestMapping("gGetCollect")
    public JSONObject gGetCollect(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
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
            List<JSONObject> list = sGetCollect.gGetCollect(jsonValueClass);

            result.setCollect(list);
        }

        return Tool.classToJson(result);
    }
}
