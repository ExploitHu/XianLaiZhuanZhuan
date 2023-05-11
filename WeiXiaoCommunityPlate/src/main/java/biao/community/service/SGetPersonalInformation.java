package biao.community.service;

import biao.community.controller.CRegister;
import biao.community.information.port1_4.UpdateUserIfmt;
import biao.community.information.port1_4.UserIfmt;
import biao.community.information.port5_6__9.AttcIfmt;
import biao.community.time.GetTime;
import biao.community.tool.Tool;
import biao.community.information.port1_1.UserInformation;
import biao.community.dao.DGetPersonalInformation;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import biao.community.information.port1_1.JsonValue;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class SGetPersonalInformation {

    @Autowired
    DGetPersonalInformation dGetPersonalInformation;

    public JSONObject getInformation(JsonValue jsonValueClass) {

        UserInformation  userInformation = dGetPersonalInformation.getInformation(Integer.parseInt(jsonValueClass.getaU_id()));

        JSONObject jsonResult = Tool.classToJson(userInformation);

        jsonResult.put("isBooked",dGetPersonalInformation.isBook(jsonValueClass));

        return jsonResult;
    }

    public int getUserCreditScore(String u_id){

        return dGetPersonalInformation.getUserCreditScore(u_id);
    }

    public JSONObject updateUserIfmt(UpdateUserIfmt uIft, MultipartFile file){

        if(file!= null){
            //存储现在的照片
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
            String endPath = GetTime.antonymFormatTime(GetTime.getWebsiteDatetime("http://www.ntsc.ac.cn")) + Tool.randomNumberAlphabet(3) +nameSuffix;
            String imagePath = CRegister.InitAddress + CRegister.MidAddress + endPath;
            File file2=new File(imagePath);
            System.out.println(file2.getAbsolutePath());


            //获取旧头像地址
            String oldHead = dGetPersonalInformation.getUserHead(uIft.getU_id());
            if(oldHead.equals("/head/defaultAvatar.jpg")){
                oldHead= "";
            }


            String newHeadAddress ="";

            //存储新照片
            //将文件保存你到file2中
            //需要获取绝对路径
            try{
                file.transferTo(file2.getAbsoluteFile());
                //记录相对路径，准备写入到数据库中
                newHeadAddress = CRegister.MidAddress + endPath;
            }catch (Exception exception){
                exception.printStackTrace();
                newHeadAddress = "/head/defaultAvatar.jpg";
            }

            //更改数据库图片地址
            dGetPersonalInformation.updateUHead(uIft.getU_id(),newHeadAddress);

            //删除旧照片
            File myObj = new File(CRegister.InitAddress + oldHead);
            if (myObj.delete()) {
                System.out.println("删除照片"+myObj.getPath());
                myObj.getName();
            }

        }

        if(uIft.getU_nickname()!=null){
            //update昵称
            dGetPersonalInformation.updateUNickname(uIft.getU_id(),uIft.getU_nickname());

        }

        if(uIft.getU_tel()!=null){
            //更改Tel（电话）
            dGetPersonalInformation.updateUTel(uIft.getU_id(),uIft.getU_tel());
        }

        if(uIft.getU_sex()!=null){
            if(uIft.getU_sex().equals("男")||uIft.getU_sex().equals("女")||uIft.getU_sex().equals("保密")){
                //更改性别
                dGetPersonalInformation.updateUSex(uIft.getU_id(),uIft.getU_sex());
            }
        }

        if(uIft.getU_like()!=null){
            //更改个人说明
            dGetPersonalInformation.updateULike(uIft.getU_id(),uIft.getU_like());

        }

        if(uIft.getU_address()!=null){
            //更改地址
            dGetPersonalInformation.updateUAddress(uIft.getU_id(),uIft.getU_address());

        }

        if(uIft.getU_birth()!=null){
            //更改生日
            dGetPersonalInformation.updateUBirth(uIft.getU_id(),uIft.getU_birth());
        }

        UserIfmt userIfmt = dGetPersonalInformation.getUserIfmt(uIft.getU_id());

        return Tool.classToJson(userIfmt);

    }

}
