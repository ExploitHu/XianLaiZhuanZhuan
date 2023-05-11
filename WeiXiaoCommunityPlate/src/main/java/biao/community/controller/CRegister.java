package biao.community.controller;

import biao.community.dao.DRegister;
import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port4.Result4_1;
import biao.community.information.port4.UserId;
import biao.community.information.port5_1.RegisterMessage;
import biao.community.information.port5_1.Result;
import biao.community.information.port5_1.UpdatePass;
import biao.community.service.SRegister;
import biao.community.time.GetTime;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;

@CrossOrigin
@RestController
public class CRegister {

    public static final String InitAddress = "/root/images";

    public static final String MidAddress = "/head/";

    @Autowired
    SRegister sRegister;
    @Autowired
    DRegister dRegister;

    @Autowired
    Examine examine;



    @PostMapping(value = "/register",headers = "content-type=multipart/form-data")
    public JSONObject test(@RequestHeader("sKey") String sKey,MultipartRequest request,@RequestParam("data") String registerMessage) {


        MultipartFile file = request.getFile("file");

        Result result = new Result();
        String error = "";
        String id = "";
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(registerMessage);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        RegisterMessage message = JSONObject.toJavaObject(jsonObject1, RegisterMessage.class);
        if(!DESUtils.checkSKey(MD5.Md5Lower32(registerMessage),sKey)){
            error = "请检查sKey";
        }else {
            //检查传来的传来的数据中必传数据是否正确

            //1、检查邮箱的不为空
            if(message.getU_email().length()>0){
                //2、检查密码的不为空
                if (message.getU_pass().length()>0){
                    //3、检查校区的正确性
                    if(dRegister.examineCity(message.getU_city())){
                        error = "校区代码错误";
                    }
                }else {
                    error = "密码为空";
                }
            }else{
                error = "邮箱为空";
            }

            //检查邮箱是否重复注册
            if(dRegister.checkEmail(message.getU_email())){
                error = "邮箱已注册";
            }


            //检查生日格式
            for (int i = 0; i < message.getU_birth().length(); i++){
                if(message.getU_birth().charAt(i) < '0'||message.getU_birth().charAt(i) > '9' || message.getU_birth().length() != 8){
                    error = "生日格式错误";
                    break;
                }
            }

        }

        //传值有误直接返回错误信息给客户端
        if(error.length()>0){
            result.setU_id("");
            result.setError(error);
            Information information = new Information();
            information.setData(Tool.classToJson(result));
            return Tool.classToJson(Tool.classToJson(information));
        }

        //生日格式转换
        StringBuffer stringBuffer = new StringBuffer(message.getU_birth());
        stringBuffer.insert(4,"-");
        stringBuffer.insert(7,"-");
        message.setU_birth(stringBuffer.toString());



        if(file == null){
            message.setU_head("/head/defaultAvatar.jpg");
        }else{
            //截取后缀名
            String nameSuffix = "";
            for (int i = file.getOriginalFilename().length() - 1; i >= 0 ; i--)
            {
                if(file.getOriginalFilename().charAt(i) != '.'){
                    nameSuffix = file.getOriginalFilename().charAt(i) + nameSuffix;
                }else{
                    nameSuffix = file.getOriginalFilename().charAt(i) + nameSuffix;
                    break;
                }
            }
            //  /root/images/
            String endPath = GetTime.antonymFormatTime(GetTime.getWebsiteDatetime("http://www.ntsc.ac.cn")) + Tool.randomNumberAlphabet(3) +nameSuffix;
            String imagePath = CRegister.InitAddress + CRegister.MidAddress + endPath;

            //imagePath = "G:/aa.jpg";

            File file2=new File(imagePath);
            System.out.println(file2.getAbsolutePath());
            //将文件保存你到file2中
            //需要获取绝对路径
            try{
                file.transferTo(file2.getAbsoluteFile());
                //记录相对路径，准备写入到数据库中
                message.setU_head(CRegister.MidAddress + endPath);
            }catch (Exception exception){
                exception.printStackTrace();
                message.setU_head("/head/defaultAvatar.jpg");
            }
        }

        id = sRegister.establishUserMessage(message);

        //非常正常返回值
        if(id.length() != 8)
        {
            result.setU_id("");
            result.setError(id);

            Information information = new Information();
            information.setData(Tool.classToJson(Tool.classToJson(result)));

            return Tool.classToJson(Tool.classToJson(information));
        }else{
            //正常注册
            result.setU_id(id);
            //删除用户所有验证码
            //System.out.println(CSendVerificationCode.verificationCode.get(message.getU_email()));
            CSendVerificationCode.verificationCode.remove(message.getU_email());
            //System.out.println(CSendVerificationCode.verificationCode.get(message.getU_email()));
        }

        Information information = new Information();
        information.setData(Tool.classToJson(Tool.classToJson(result)));
        return Tool.classToJson(Tool.classToJson(information));
    }


    @RequestMapping("updatePass")
    public JSONObject updatePass(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/){
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        UpdatePass updatePass = JSONObject.toJavaObject(jsonObject1, UpdatePass.class);

        JSONObject result = new JSONObject();
        Information information = new Information();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.put("state","sKey错误");
        }else {
            //检查u_id
            if(examine.exUId(Integer.parseInt(updatePass.getU_id()))){
                result.put("state","请检查u_id");

            }
        }

        if(result.get("state") == null){
            //更新用户密码
            dRegister.updatePass(updatePass);

            result.put("state","success");
        }

        information.setData(result);
        return Tool.classToJson(information);
    }
}
