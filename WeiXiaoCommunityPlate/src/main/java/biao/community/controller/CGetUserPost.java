package biao.community.controller;


import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port2_13and3_12.JsonValue;
import biao.community.information.port2_13and3_12.Result;
import biao.community.service.SGetUserPost;
import biao.community.time.GetTime;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CGetUserPost {

    @Autowired
    SGetUserPost sGetUserPost;

    @Autowired
    Examine examine;

    //获取目标用户发布的社区帖子
    @RequestMapping("getUserBPost")
    public JSONObject getUserBPost(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/) {
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();
        Information information = new Information();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setError("请检查sKey");
        }else {
            //检查u_id
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                if(!jsonValueClass.getU_id().equals("0")){
                    result.setError("请检查u_id");
                }
            }
            //检查aim_u_id
            if(examine.exUId(Integer.parseInt(jsonValueClass.getAim_u_id()))){
                result.setError("请检查aim_u_id");
            }

            //检查time  处理time
            if (jsonValueClass.getTime().length() == 0){
                jsonValueClass.setTime("now");
            }else if(jsonValueClass.getTime().length() != 14){
                //长度出问题
                result.setError("请检查time");
            }else if(!GetTime.examineYMD(jsonValueClass.getTime().substring(0,8),"yyyyMMdd")){
                //年月日出问题
                result.setError("请检查time");
            }else {
                jsonValueClass.setTime(GetTime.formatTime(jsonValueClass.getTime()));
            }

            //检查sum
            if(jsonValueClass.getSum() <= 0 || jsonValueClass.getSum() > 20){
                result.setError("请检查sum");
            }
        }


        //传入信息有误
        if(result.getError().length() != 0){
            information.setData(Tool.classToJson(result));
            return Tool.classToJson(information);
        }

        List<JSONObject> list = sGetUserPost.getUserBPost(jsonValueClass,result);

        result.setU_id(jsonValueClass.getU_id());
        result.setPost(list);

        information.setData(Tool.classToJson(result));
        JSONObject temp = Tool.classToJson(information);
        return temp;
    }


    @RequestMapping("getUserGPost")
    public JSONObject getUserGPost(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();
        Information information = new Information();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setError("请检查sKey");
        }else {
            //检查u_id
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                if(!jsonValueClass.getU_id().equals("0")){
                    result.setError("请检查u_id");
                }
            }
            //检查aim_u_id
            if(examine.exUId(Integer.parseInt(jsonValueClass.getAim_u_id()))){
                result.setError("请检查aim_u_id");
            }

            //检查time  处理time
            if (jsonValueClass.getTime().length() == 0){
                jsonValueClass.setTime("now");
            }else if(jsonValueClass.getTime().length() != 14){
                //长度出问题
                result.setError("请检查time");
            }else if(!GetTime.examineYMD(jsonValueClass.getTime().substring(0,8),"yyyyMMdd")){
                //年月日出问题
                result.setError("请检查time");
            }else {
                jsonValueClass.setTime(GetTime.formatTime(jsonValueClass.getTime()));
            }

            //检查sum
            if(jsonValueClass.getSum() <= 0 || jsonValueClass.getSum() > 20){
                result.setError("请检查sum");
            }
        }


        //传入信息有误
        if(result.getError().length() != 0){
            information.setData(Tool.classToJson(result));
            return Tool.classToJson(information);
        }

        List<JSONObject> list = sGetUserPost.getUserGPost(jsonValueClass,result);

        result.setU_id(jsonValueClass.getU_id());
        result.setPost(list);

        information.setData(Tool.classToJson(result));
        return Tool.classToJson(information);
    }


}
