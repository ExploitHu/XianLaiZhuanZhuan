package biao.community.controller;

import biao.community.information.Information;
import biao.community.information.port5_1.RegisterMessage;
import biao.community.information.port5_3.Result;
import biao.community.information.port5_3.VerificationCode;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CEmailVerification {
    /**
     * 接口5.3邮箱验证
     * 验证验证码的有效性
     * @param message 包含验证码、验证邮箱
     * @return yes、no
     */
    @RequestMapping("emailVerification")
    public JSONObject create(@RequestHeader("sKey") String sKey, @RequestBody String message/**postJson是json字符串**/) {
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(message);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        VerificationCode verificationCode = JSONObject.toJavaObject(jsonObject1, VerificationCode.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(message),sKey)){
            result.setState("sKey错误");
        }else {
            List list = CSendVerificationCode.verificationCode.get(verificationCode.getEmail());

            //将发送来的验证码全部转换为大写方便比对
            System.out.println(verificationCode.getVerifyCode());
            verificationCode.setVerifyCode(verificationCode.getVerifyCode().toUpperCase());
            System.out.println(verificationCode.getVerifyCode());

            if (list == null) {
                Information information = new Information();

                result.setState("no");
                information.setData(Tool.classToJson(result));

                return Tool.classToJson(information);
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(verificationCode.getVerifyCode())) {
                    Information information = new Information();

                    information.setData(Tool.classToJson(result));

                    return Tool.classToJson(information);

                }
            }
        }

        Information information = new Information();


        result.setState("no");
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);
    }
}
