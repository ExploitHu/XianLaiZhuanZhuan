package biao.community.controller;

import biao.community.information.Information;
import biao.community.information.port5_2.ReplyState;
import biao.community.information.port5_2.VerificationEmail;
import biao.community.tool.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class CSendVerificationCode {

    @Autowired
    Send163Email emailTool;

    //验证码存储变量
    public static Map<String, List<String>> verificationCode = new HashMap<>();


    /**
     * 接口5.2  发送验证码给目标邮箱
     * @param commentPostJson  包含目标邮箱
     * @return  发送验证码是否成功"success"/"defeated"
     */
    @RequestMapping("/sendVerificationCode")
    public String create(@RequestHeader("sKey") String sKey, @RequestBody String commentPostJson/**postJson是json字符串**/) {


        JSONObject jsonObject = JSONObject.parseObject(commentPostJson);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        VerificationEmail verificationEmail = JSONObject.toJavaObject(jsonObject1, VerificationEmail.class);

        ReplyState replyState = new ReplyState();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(commentPostJson),sKey)){
            replyState.setState("请检查sKey");

        }else {
            //发送验证码
            try {
                //向指定邮箱发送验证码
                String str = emailTool.send(verificationEmail.getEmail());
                //记录验证码信息
                if (CSendVerificationCode.verificationCode.containsKey(verificationEmail.getEmail())) {
                    CSendVerificationCode.verificationCode.get(verificationEmail.getEmail()).add(str);
                } else {
                    List<String> list = new ArrayList<String>();
                    list.add(str);
                    CSendVerificationCode.verificationCode.put(verificationEmail.getEmail(), list);
                }
                //新建一个定时清除(验证码有效时间)
                ExpiredVerificationCode.timeToDelete(verificationEmail.getEmail(), 60 * 5);


            } catch (Exception exception) {
                replyState.setState("defeated");
                Information information = new Information();
                information.setData(Tool.classToJson(replyState));
                exception.printStackTrace();
                return Tool.classToString(information);
            }
        }

        Information information = new Information();
        information.setData(Tool.classToJson(replyState));

        return Tool.classToString(information);
    }


}
