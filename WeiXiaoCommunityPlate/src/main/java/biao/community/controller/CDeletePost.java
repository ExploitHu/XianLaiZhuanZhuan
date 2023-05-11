package biao.community.controller;

import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port2_7.JsonValue;
import biao.community.service.SDeletePost;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
public class CDeletePost {

    @Autowired
    Examine examine;

    @Autowired
    SDeletePost sDeletePost;

    @RequestMapping("/bDeletePost")
    public JSONObject bDeletePost(@RequestHeader("sKey") String sKey, @RequestBody String jsonValue/**postJson是json字符串**/){
        JSONObject jsonObject = JSONObject.parseObject(jsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        String u_id = jsonObject1.get("u_id").toString();
        String bt_id = jsonObject1.get("bt_id").toString();

        JSONObject result = new JSONObject();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(jsonValue),sKey)){
            result.put("state","请检查sKey");
        }

        if(examine.UserIssueBPost(bt_id,u_id)){
            result.put("state","请检查u_id、bt_id");
        }


        if(result.get("state")==null){
            result.put("state","success");
            sDeletePost.bSDeletePost(bt_id);
        }
        Information information = new Information();
        information.setData(result);

        return Tool.classToJson(information);
    }

    @RequestMapping("/gDeletePost")
    public JSONObject gDeletePost(@RequestHeader("sKey") String sKey, @RequestBody String jsonValue/**postJson是json字符串**/){
        JSONObject jsonObject = JSONObject.parseObject(jsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        String u_id = jsonObject1.get("u_id").toString();
        String g_id = jsonObject1.get("g_id").toString();

        JSONObject result = new JSONObject();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(jsonValue),sKey)){
            result.put("state","请检查sKey");
        }

        if(examine.UserIssueGPost(g_id,u_id)){
            result.put("state","请检查u_id、g_id");
        }


        if(result.get("state")==null){
            result.put("state","success");
            sDeletePost.gSDeletePost(g_id);
        }

        Information information = new Information();
        information.setData(result);

        return Tool.classToJson(information);
    }
}
