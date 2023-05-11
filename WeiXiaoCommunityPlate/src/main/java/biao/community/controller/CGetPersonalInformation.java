package biao.community.controller;

import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port1_1.JsonValue;
import biao.community.information.port1_1.Result;
import biao.community.information.port1_1.UserInformation;
import biao.community.information.port1_4.UpdateUserIfmt;
import biao.community.time.GetTime;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import biao.community.service.SGetPersonalInformation;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;


@CrossOrigin
@RestController
public class CGetPersonalInformation {

    @Autowired
    SGetPersonalInformation sGetPersonalInformation;

    @Autowired
    Examine examine;

    /**
     * 接口1.0  查询用户个人信息
     * 根据用户id查询用户信息并返回给客户端
     * @return 个人信息列表
     */
    @RequestMapping("/getPersonalInformation")
    public JSONObject getPersonalInformation(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {

        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setErrorMessage("请检查sKey");
            result.setCode(0);
        }else {
            if (examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))) {
                result.setErrorMessage("请检查u_id");
                result.setCode(0);
            }
            if (examine.exUId(Integer.parseInt(jsonValueClass.getaU_id()))) {
                result.setErrorMessage("请检查aU_id");
                result.setCode(0);
            }
        }



        if (result.getErrorMessage().equals("success")) {
            result.setUser(sGetPersonalInformation.getInformation(jsonValueClass));
            result.setCode(200);
        }

        Information information = new Information();

        information.setData(Tool.classToJson(result));

        //根据id获取用户信息
        return Tool.classToJson(information);

    }

    /***
     * 1.3获取用户信誉积分信息
     * @param JsonValue
     * @return
     */
    @RequestMapping("/getUserCreditScore")
    public JSONObject getUserCreditScore(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        String u_id = jsonObject1.get("u_id").toString();

        JSONObject json = new JSONObject();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            json.put("state","请检查sKey");
        }else {
            if(examine.exUId(Integer.parseInt(u_id))){
                json.put("state","请检查u_id");
            }
        }

        if(json.get("state")==null){
            json.put("state","success");
            json.put("u_xpoint",sGetPersonalInformation.getUserCreditScore(u_id));
        }

        Information information = new Information();
        information.setData(json);

        return Tool.classToJson(information);
    }

    /***
     * //1.4更新用户信息
     * @param
     * @return
     */
    @PostMapping(value = "/updateUserIfmt",headers = "content-type=multipart/form-data")
    public JSONObject updateUserIfmt(@RequestHeader("sKey") String sKey, MultipartRequest request, @RequestParam("data") String registerMessage) {
        //System.out.println(registerMessage);

        JSONObject jsonObject = JSONObject.parseObject(registerMessage);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        UpdateUserIfmt userInformation = JSONObject.toJavaObject(jsonObject1, UpdateUserIfmt.class);

        JSONObject json = new JSONObject();
        MultipartFile file = null;
        if(!DESUtils.checkSKey(MD5.Md5Lower32(registerMessage),sKey)){
            json.put("state", "请检查sKey");
        }else{
            if(examine.exUId(Integer.parseInt(userInformation.getU_id()))){
                json.put("state","请检查u_id");
            }
            if(userInformation.getU_birth()!=null){
                if(!GetTime.examineYMD(userInformation.getU_birth(),"yyyy-MM-dd")){
                    json.put("state","请检查u_birth");
                }
            }
            file = request.getFile("headImage");
        }


        if(json.get("state")==null){
            json.put("state","success");
            json.put("userIfmt",sGetPersonalInformation.updateUserIfmt(userInformation, file));
        }

        Information information = new Information();
        information.setData(json);

        return Tool.classToJson(information);

    }

    /***
     * //1.4更新用户信息(不包含头像)
     * @param
     * @return
     */

    @RequestMapping("/updateUserCIfmt")
    public JSONObject updateUserCIfmt(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {

        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        UpdateUserIfmt userInformation = JSONObject.toJavaObject(jsonObject1, UpdateUserIfmt.class);

        JSONObject json = new JSONObject();
        MultipartFile file = null;
        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            json.put("state", "请检查sKey");
        }else{
            if(examine.exUId(Integer.parseInt(userInformation.getU_id()))){
                json.put("state","请检查u_id");
            }
            if(userInformation.getU_birth()!=null){
                if(!GetTime.examineYMD(userInformation.getU_birth(),"yyyy-MM-dd")){
                    json.put("state","请检查u_birth");
                }
            }
        }


        if(json.get("state")==null){
            json.put("state","success");
            json.put("userIfmt",sGetPersonalInformation.updateUserIfmt(userInformation,null));
        }

        Information information = new Information();
        information.setData(json);

        return Tool.classToJson(information);

    }




}

