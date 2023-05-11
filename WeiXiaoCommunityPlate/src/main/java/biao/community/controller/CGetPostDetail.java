package biao.community.controller;

import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port3_2.JsonValue;
import biao.community.information.port3_2.Result;
import biao.community.service.SGetPostDetail;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
//获取帖子详细信息
public class CGetPostDetail {

    @Autowired
    SGetPostDetail sGetPostDetail;

    @Autowired
    Examine examine;


    @RequestMapping("getBPostetail")
    public JSONObject getBPostetail(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setErrorMessage("请检查sKey");
            result.setCode(0);
        }else {
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                if(!jsonValueClass.getU_id().equals("0")){
                    result.setErrorMessage("请检查u_id");
                }
            }
            if(examine.exBtId(Integer.parseInt(jsonValueClass.getBt_id()))){
                result.setErrorMessage("请检查bt_id");
            }
        }

        if(result.getErrorMessage().equals("success")){
            result.setDetailInformation(Tool.classToJson(sGetPostDetail.getBPostDetail(jsonValueClass)));
            result.setCode(200);
        }else{
            result.setCode(0);
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);

    }

    @RequestMapping("getGPostetail")
    public JSONObject getGPostetail(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setErrorMessage("请检查sKey");
            result.setCode(0);
        }else {
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                if(!jsonValueClass.getU_id().equals("0")){
                    result.setErrorMessage("请检查u_id");
                }
            }
            if(examine.exGId(Integer.parseInt(jsonValueClass.getG_id()))){
                result.setErrorMessage("请检查g_id");
            }
        }

        if(result.getErrorMessage().equals("success")){
            result.setDetailInformation(Tool.classToJson(sGetPostDetail.getGPostDetail(jsonValueClass)));
            result.setCode(200);
        }else{
            result.setCode(0);
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);
    }
}

