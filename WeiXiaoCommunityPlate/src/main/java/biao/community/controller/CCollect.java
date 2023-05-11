package biao.community.controller;


import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port2_12and3_11.JsonValue;
import biao.community.information.port2_12and3_11.Result;
import biao.community.service.SCollect;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CCollect {

    @Autowired
    SCollect sAddCollect;

    @Autowired
    Examine examine;

    @RequestMapping("bAddCollect")
    public JSONObject bAddCollect(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/){
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

            //检查bg_id
            if(examine.exBtId(Integer.parseInt(jsonValueClass.getBg_id()))){
                result.setState("请检查bg_id");
            }
        }


        if(result.getState().equals("success")){
            sAddCollect.bAddCollect(jsonValueClass);
        }

        Information information = new Information();

        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);

    }

    @RequestMapping("gAddCollect")
    public JSONObject gAddCollect(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/){
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

            //检查bg_id
            if(examine.exGId(Integer.parseInt(jsonValueClass.getBg_id()))){
                result.setState("请检查bg_id");
            }
        }

        if(result.getState().equals("success")){
            sAddCollect.gAddCollect(jsonValueClass);
        }

        Information information = new Information();

        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);

    }


    @RequestMapping("bDelCollect")
    public JSONObject bDelCollect(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/){
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

            //检查bg_id
            if(examine.exBtId(Integer.parseInt(jsonValueClass.getBg_id()))){
                result.setState("请检查bg_id");
            }
        }


        if(result.getState().equals("success")){
            sAddCollect.bDelCollect(jsonValueClass);
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);
    }

    @RequestMapping("gDelCollect")
    public JSONObject gDelCollect(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/){
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

            //检查bg_id
            if(examine.exGId(Integer.parseInt(jsonValueClass.getBg_id()))){
                result.setState("请检查bg_id");
            }
        }



        if(result.getState().equals("success")){
            sAddCollect.gDelCollect(jsonValueClass);
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);
    }
}
