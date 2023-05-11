package biao.community.controller;

import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port2_13and3_12.Result;
import biao.community.information.port2_14and3_14.JsonValue;
import biao.community.service.SGetPost;
import biao.community.time.GetTime;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
public class CGetPost {

    @Autowired
    SGetPost sGetCommunityDynamic;

    @Autowired
    Examine examine;

    //商品界面主接口
    //功能：获取sum个社区帖子的信息
    @RequestMapping("getCommunityDynamic")
    public JSONObject getCommunityDynamic(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        biao.community.information.port2_1and3_1.JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, biao.community.information.port2_1and3_1.JsonValue.class);

        biao.community.information.port2_1and3_1.Result result = new biao.community.information.port2_1and3_1.Result();

        //处理校区
        if(jsonValueClass.getU_city()==null){
            jsonValueClass.setU_city("");
        }

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setErrorMessage("请检查sKey");
            result.setCode(0);
        }else {
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                if(!jsonValueClass.getU_id().equals("0")){
                    result.setErrorMessage("请检查u_id");
                }
            }
            if(jsonValueClass.getSum()>20||jsonValueClass.getSum()<0){
                result.setErrorMessage("请检查sum");
            }
        }


        if(result.getErrorMessage().equals("success")){

            if(jsonValueClass.getSlm().length() == 0){
                jsonValueClass.setRecommendValue(146);
                jsonValueClass.setTime("now");
                result.setPost(sGetCommunityDynamic.getCommunityDynamic(jsonValueClass,result));
            }else {
                List<String> listSlm = Arrays.asList(jsonValueClass.getSlm().split(","));
                if(listSlm.size()==2){
                    //设置时间  推荐值
                    jsonValueClass.setRecommendValue(Float.parseFloat(listSlm.get(0)));
                    jsonValueClass.setTime(listSlm.get(1));
                    result.setPost(sGetCommunityDynamic.getCommunityDynamic(jsonValueClass,result));
                    result.setCode(200);
                }else{
                    result.setErrorMessage("请检查Slm");
                    result.setCode(0);
                }

            }

        }

        Information information = new Information();

        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);
    }

    //商品界面主接口
    //功能：获取sum个商品的信息
    @RequestMapping("getGoodDetail")
    public JSONObject getGoodDetail(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {

        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        biao.community.information.port2_1and3_1.JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, biao.community.information.port2_1and3_1.JsonValue.class);

        biao.community.information.port2_1and3_1.Result result = new biao.community.information.port2_1and3_1.Result();


        //处理校区
        if(jsonValueClass.getU_city()==null){
            jsonValueClass.setU_city("");
        }

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setErrorMessage("请检查sKey");
            result.setCode(0);
        }else {
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                if(!jsonValueClass.getU_id().equals("0")){
                    result.setErrorMessage("请检查u_id");
                }
                result.setCode(0);
            }
            if(jsonValueClass.getSum()>20||jsonValueClass.getSum()<0){
                result.setErrorMessage("请检查sum");
                result.setCode(0);
            }
        }

        if(result.getErrorMessage().equals("success")){
            if(jsonValueClass.getSlm().length() == 0){
                jsonValueClass.setRecommendValue(146);
                jsonValueClass.setTime("now");
                result.setPost(sGetCommunityDynamic.getGoodDetail(jsonValueClass,result));
            }else {
                List<String> listSlm = Arrays.asList(jsonValueClass.getSlm().split(","));
                if(listSlm.size()==2){
                    //设置时间  推荐值
                    jsonValueClass.setRecommendValue(Float.parseFloat(listSlm.get(0)));
                    jsonValueClass.setTime(listSlm.get(1));
                    result.setPost(sGetCommunityDynamic.getGoodDetail(jsonValueClass,result));
                    result.setCode(200);
                }else{
                    result.setErrorMessage("请检查Slm");
                    result.setCode(0);
                }

            }
        }

        Information information = new Information();

        information.setData(Tool.classToJson(result));



        return Tool.classToJson(information);

    }


    //功能：根据id获取用户时间time之前发布的sum个社区帖子
    @RequestMapping("getAttCommunityDynamic")
    public JSONObject getAttCommunityDynamic(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
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
                result.setError("请检查u_id");
            }
            result.setU_id(jsonValueClass.getU_id());
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

        List<JSONObject> list = sGetCommunityDynamic.getAttCommunityDynamic(jsonValueClass,result);

        result.setPost(list);

        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);

    }

    //功能：根据id获取用户时间time之前发布的sum个社区帖子
    @RequestMapping("getAttGPost")
    public JSONObject getAttGPost(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
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
                result.setError("请检查u_id");
            }
            result.setU_id(jsonValueClass.getU_id());
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

        List<JSONObject> list = sGetCommunityDynamic.getAttGPost(jsonValueClass,result);

        result.setPost(list);

        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);

    }

}
