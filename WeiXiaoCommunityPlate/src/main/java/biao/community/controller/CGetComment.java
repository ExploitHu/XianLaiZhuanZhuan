package biao.community.controller;

import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port2_3.JsonValue;
import biao.community.information.port2_3.Result;
import biao.community.service.SGetComment;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CGetComment {

    @Autowired
    SGetComment sGetComment;

    @Autowired
    Examine examine;
    /**
     * 接口2.3查询传入bt_id对应的帖子的评论
     * @return 帖子的所有评论（子评论、主评论）
     */
    @RequestMapping("getBComment")
    public JSONObject getBComment(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setErrorMessage("请检查sKey");
            result.setCode(0);
        }else {
            //bt_id
            if(examine.exBtId(Integer.parseInt(jsonValueClass.getBt_id()))){
                result.setErrorMessage("请检查bt_id");
                result.setCode(0);
            }
        }

        if(result.getErrorMessage().equals("success")){
            result.setCode(200);
            result.setCommentList(sGetComment.getBComment(Integer.parseInt(jsonValueClass.getBt_id())));
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);
    }

    //获取商品帖子收到的评论
    @RequestMapping("getGComment")
    public JSONObject getGComment(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();


        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setErrorMessage("请检查sKey");
            result.setCode(0);
        }else {
            //检查g_id
            if(examine.exGId(Integer.parseInt(jsonValueClass.getG_id()))){
                result.setErrorMessage("请检查g_id");
                result.setCode(0);
            }
        }

        if(result.getErrorMessage().equals("success")){
            result.setCode(200);
            result.setCommentList(sGetComment.getGComment(Integer.parseInt(jsonValueClass.getG_id())));
        }

        Information information = new Information();
        JSONObject resultDataJson = Tool.classToJson(result);
        resultDataJson.put("g_id",jsonValueClass.getG_id());
        resultDataJson.remove("bt_id");
        information.setData(resultDataJson);

        return Tool.classToJson(information);
    }


    //获取用户收到的社区帖子未读评论（被@的）
    @RequestMapping("getBToBeRead")
    public JSONObject getBToBeRead(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        String u_id = jsonObject1.get("u_id").toString();
        String slm = jsonObject1.get("slm").toString();

        biao.community.information.port2_17and3_16.Result result = new biao.community.information.port2_17and3_16.Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("请检查sKey");
        }else if(examine.exUId(Integer.parseInt(u_id))){
            result.setState("请检查u_id");
        }

        if(result.getState().equals("success")){
            sGetComment.getBToBeRead(u_id, slm,result);
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);

    }

    //获取用户收到的商城帖子未读评论（被@的）
    @RequestMapping("getGToBeRead")
    public JSONObject getGToBeRead(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        String u_id = jsonObject1.get("u_id").toString();
        String slm = jsonObject1.get("slm").toString();

        biao.community.information.port2_17and3_16.Result result = new biao.community.information.port2_17and3_16.Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("请检查sKey");
        }else if(examine.exUId(Integer.parseInt(u_id))){
            result.setState("请检查u_id");
        }

        if(result.getState().equals("success")){
            sGetComment.getGToBeRead(u_id,slm,result);
        }else{
            result.setSlm(null);
        }



        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);

    }

}


