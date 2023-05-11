package biao.community.controller;


import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port2_8and3_7.JsonValue;
import biao.community.service.SGetClassify;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CGetClassify {

    @Autowired
    SGetClassify sGetClassify;

    @Autowired
    Examine examine;

    @RequestMapping("getBClassify")
    public JSONObject getBClassify(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/){
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Information information = new Information();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            information.setData("请检查sKey");
        }else {
            //检查u_id
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                information.setData("请检查u_id");
            }else{
                information.setData(sGetClassify.getBClassify());
            }
        }


        return Tool.classToJson(information);

    }

    @RequestMapping("getGClassify")
    public JSONObject getGClassify(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/){
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Information information = new Information();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            information.setData("请检查sKey");
        }else {
            //检查u_id
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                information.setData("请检查u_id");
            }else{
                information.setData(sGetClassify.getGClassify());
            }
        }

        return Tool.classToJson(information);
    }

}
