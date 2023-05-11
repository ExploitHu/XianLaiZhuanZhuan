package biao.community.service;

import biao.community.dao.DLogIn;
import biao.community.information.port5_5.LogInMessage;
import biao.community.information.port5_5.PassAndUId;
import biao.community.tool.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SLogIn {

    @Autowired
    DLogIn dLogIn;

    public String logIn(LogInMessage logInMessage){
        PassAndUId passAndUId;
        //获取用户密码
        if(logInMessage.getU_email()==null){
            passAndUId = dLogIn.uGetPassworld(logInMessage.getU_id());
        }else if(logInMessage.getU_email().length()>0){
            passAndUId = dLogIn.eGetPassworld(logInMessage.getU_email());
        }else {
            passAndUId = dLogIn.uGetPassworld(logInMessage.getU_id());
        }



        if(passAndUId == null){
            return null;
        }
        //将得到的密码md5加密
        String passworld = MD5.Md5Lower32(passAndUId.getU_pass());

        //验证密码
        if(passworld.equals(logInMessage.getPassworldMD5())){
            return passAndUId.getU_id();
        }else{
            return null;
        }

    }

}
