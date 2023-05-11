package biao.community.controller;


import biao.community.dao.Examine;
import biao.community.information.Information;

import biao.community.information.port3_5.JsonValue;
import biao.community.information.port3_5.Result;
import biao.community.service.SSetDealPost;
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
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class CSetDealPost {

    private static final String InitAddress = "/root/images";

    private static final String MidAddress = "/gSell/";

    //最大上传图片的数量
    private static final int MAXIMAGE = 6;

    @Autowired
    Examine examine;

    @Autowired
    SSetDealPost sSetDealPost;

    @PostMapping(value = "/setDealPost",headers = "content-type=multipart/form-data")
    public JSONObject create(@RequestHeader("sKey") String sKey, MultipartRequest request, @RequestParam("data") String registerMessage) {

        Result result = new Result();
        String erorr= "";

        //json转class
        JSONObject jsonObject = JSONObject.parseObject(registerMessage);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);


        if(!DESUtils.checkSKey(MD5.Md5Lower32(registerMessage),sKey)){
            result.setError("请检查sKey");
        }else {
            //检查u_id是否正确
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                erorr = "请检查u_id";
            }

            //检查g_class是否正确
            if(examine.exGClass(jsonValueClass.getG_class())){
                erorr = "请检查g_class";
            }
        }

        if(erorr.length() == 0){

            List<MultipartFile> files = new ArrayList<>();

            //从request提取照片文件，转为List<MultipartFile>类型
            int tempName = 0;
            while (request.getFile("image" + String.valueOf(tempName)) != null){
                files.add(request.getFile("image" + String.valueOf(tempName)));
                tempName++;
                if(tempName == MAXIMAGE){
                    break;
                }
            }

            //相对地址存储
            List<String> listPath = new ArrayList<>();

            //截取后缀名
            String nameSuffix = "";

            for (MultipartFile file:files) {
                nameSuffix = "";
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
                String imagePath = CSetDealPost.InitAddress + CSetDealPost.MidAddress + endPath;
                System.out.println(imagePath);
                //imagePath = "G:/" + endPath;

                File file2=new File(imagePath);
                System.out.println(file2.getAbsolutePath());
                //将文件保存你到file2中
                //需要获取绝对路径
                try{
                    file.transferTo(file2.getAbsoluteFile());
                    listPath.add(CSetDealPost.MidAddress + endPath);
                    System.out.println("存放路径:"+CSetDealPost.MidAddress + endPath);
                }catch (Exception exception){
                    exception.printStackTrace();
                    erorr = "存储图片失败";
                }
            }

            jsonValueClass.setG_image(String.join(",",listPath));

            result.setG_id(sSetDealPost.setDealPost(jsonValueClass));
        }

        result.setError(erorr);
        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);
    }
}
