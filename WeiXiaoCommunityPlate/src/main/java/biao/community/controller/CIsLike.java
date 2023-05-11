package biao.community.controller;

import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port7_1_4.JsonValue;
import biao.community.information.port7_1_4.Result;
import biao.community.service.SIsLike;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
//获取帖子详细信息
public class CIsLike {

    @Autowired
    Examine examine;

    @Autowired
    SIsLike sIsLike;

    //增加一条点赞信息
    @RequestMapping("addBIsLike")
    public JSONObject addBIsLike(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("请检查sKey");

        }else {
            if(examine.exBtId(Integer.parseInt(jsonValueClass.getBt_id()))){
                result.setState("请检查bt_id");
            }
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                result.setState("请检查u_id");
            }
        }

        if(result.getState()==null){
            //Service
            result.setState(sIsLike.addBIsLike(jsonValueClass));
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));
        return Tool.classToJson(information);
    }

    @RequestMapping("delBIsLike")
    public JSONObject delBIsLike(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);


        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("请检查sKey");

        }else {
            if(examine.exBtId(Integer.parseInt(jsonValueClass.getBt_id()))){
                result.setState("请检查bt_id");
            }
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                result.setState("请检查u_id");
            }
        }

        if(result.getState()==null){
            result.setState("success");
            //Service
            sIsLike.delBIsLike(jsonValueClass);
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));
        return Tool.classToJson(information);
    }


    @RequestMapping("addGIsLike")
    public JSONObject addGIsLike(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);


        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("请检查sKey");
        }else {
            if(examine.exGId(Integer.parseInt(jsonValueClass.getG_id()))){
                result.setState("请检查g_id");
            }
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                result.setState("请检查u_id");
            }
        }


        if(result.getState()==null){
            //Service
            result.setState(sIsLike.addGIsLike(jsonValueClass));
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));
        return Tool.classToJson(information);
    }

    @RequestMapping("delGIsLike")
    public JSONObject delGIsLike(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);


        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("请检查sKey");
        }else {
            if(examine.exGId(Integer.parseInt(jsonValueClass.getG_id()))){
                result.setState("请检查g_id");
            }
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                result.setState("请检查u_id");
            }
        }

        if(result.getState()==null){
            result.setState("success");
            //Service
            sIsLike.delGIsLike(jsonValueClass);

        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));
        return Tool.classToJson(information);
    }

}
