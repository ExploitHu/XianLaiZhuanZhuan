package biao.community.controller;


import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port4.AddFriend;
import biao.community.information.port4.Result4_1;
import biao.community.information.port4.Result4_2;
import biao.community.information.port4.UserId;
import biao.community.service.SFriends;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CFriends {

    @Autowired
    Examine examine;

    @Autowired
    SFriends sFriends;

    //4.1获取用户好友列表
    @RequestMapping("getFriends")
    public JSONObject getFriends(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/) {
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        UserId userId = JSONObject.toJavaObject(jsonObject1, UserId.class);

        Result4_1 result = new Result4_1();
        Information information = new Information();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setError("sKey错误");
        }else {
            //检查u_id
            if(examine.exUId(Integer.parseInt(userId.getU_id()))){
                result.setError("请检查u_id");
            }
        }

        if(result.getError().length() == 0){
            result.setFriendList(sFriends.getFriends(Integer.parseInt(userId.getU_id())));
        }

        information.setData(Tool.classToJson(result));
        return Tool.classToJson(information);
    }

    //4.2用户新增关注人（新增好友）
    @RequestMapping("addFriend")
    public JSONObject addFriend(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        AddFriend addFriend = JSONObject.toJavaObject(jsonObject1, AddFriend.class);

        Result4_2 result = new Result4_2();

        Information information = new Information();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("sKey错误");
        }else {
            //检查u_id
            if(examine.exUId(Integer.parseInt(addFriend.getU_id()))){
                result.setState("请检查u_id");
            }
            //检查uf_id
            if(examine.exUId(Integer.parseInt(addFriend.getUf_id()))){
                result.setState("请检查uf_id");
            }

            if(!(addFriend.getUf_source().equals("交易") || addFriend.getUf_source().equals("社区"))){
                result.setState("请检查uf_source()");
            }
        }

        //传入数据没有错误写入数据
        if(result.getState().equals("success")){
            sFriends.addFriend(addFriend);
        }

        information.setData(Tool.classToJson(result));
        return Tool.classToJson(information);
    }


    //4.2用户新增关注人（新增好友）
    @RequestMapping("delFriend")
    public JSONObject delFriend(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        AddFriend addFriend = JSONObject.toJavaObject(jsonObject1, AddFriend.class);

        Result4_2 result = new Result4_2();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("sKey错误");
        }else {
            //检查u_id
            if(examine.exUId(Integer.parseInt(addFriend.getU_id()))){
                result.setState("请检查u_id");
            }
            //检查uf_id
            if(examine.exUId(Integer.parseInt(addFriend.getUf_id()))){
                result.setState("请检查uf_id");
            }


        }

        //传入数据没有错误写入数据
        if(result.getState().equals("success")){
            sFriends.delFriend(addFriend);
        }

        Information information = new Information();

        information.setData(Tool.classToJson(result));
        return Tool.classToJson(information);

    }


}
