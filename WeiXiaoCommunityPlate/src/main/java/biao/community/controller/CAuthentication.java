package biao.community.controller;

import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port5_6__9.AttcIfmt;
import biao.community.information.port5_6__9.JsonValue;
import biao.community.information.port5_6__9.Result;
import biao.community.information.port5_6__9.UId;
import biao.community.service.SAuthentication;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class CAuthentication {

    private static final String InitAddress = "/root/images";

    private static final String MidAddress = "/AttcIfmt/";

    @Autowired
    SAuthentication sAuthentication;

    @Autowired
    Examine examine;

    //用户上认证信息
    @PostMapping(value = "/updAttcIfmt",headers = "content-type=multipart/form-data")
    public JSONObject updAttcIfmt(@RequestHeader("sKey") String sKey,MultipartRequest request, @RequestParam("data") String registerMessage) {

        //json转class
        JSONObject jsonObject = JSONObject.parseObject(registerMessage);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(registerMessage),sKey)){
            result.setState("sKey错误");
        }else {
            //判断数据格式是否正确
            if(jsonValueClass.getCollegeToClass().length() == 0 || jsonValueClass.getName().length() == 0 || jsonValueClass.getStudentID().length() == 0){
                result.setError("有数据为空");
                result.setState("defeated");
            }else{
                if(jsonValueClass.getCollegeToClass().length() > 32){
                    result.setError("collegeToClass过长");
                    result.setState("defeated");
                }
                if(jsonValueClass.getName().length() > 8){
                    result.setError("name过长");
                    result.setState("defeated");
                }
                if(jsonValueClass.getStudentID().length() > 12){
                    result.setError("studentID过长");
                    result.setState("defeated");
                }
                if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                    result.setError("请检查u_id");
                    result.setState("defeated");
                }

            }
        }



        if(result.getError().length() == 0){

            List<MultipartFile> files = new ArrayList<>();

            //从request提取照片文件，转为List<MultipartFile>类型
            files.add(request.getFile("image0"));
            files.add(request.getFile("image1"));

            //相对地址存储
            List<String> listPath = new ArrayList<>();

            //截取后缀名
            String nameSuffix = "";

            //删除原有文件，创建新文件夹
            File newFile0 =  new File(CAuthentication.InitAddress+CAuthentication.MidAddress +jsonValueClass.getU_id());
            File newFile1 =  new File(CAuthentication.InitAddress+CAuthentication.MidAddress +jsonValueClass.getU_id() + "old");

            if(newFile0.exists()){
                newFile0.renameTo(newFile1);
            }
            newFile0.mkdirs();


            char sum = 'A';

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

                String endPath = jsonValueClass.getStudentID() + sum++ + nameSuffix;
                String imagePath = CAuthentication.InitAddress + CAuthentication.MidAddress + "/" +jsonValueClass.getU_id() + "/"+ endPath;

                //imagePath = "G:/" + endPath;

                File file2=new File(imagePath);
                System.out.println(file2.getAbsolutePath());
                //将文件保存你到file2中
                //需要获取绝对路径
                try{
                    file.transferTo(file2.getAbsoluteFile());
                    listPath.add(CAuthentication.MidAddress +jsonValueClass.getU_id() + "/"+ endPath);


                    //result.setBt_id(sSetCommunityPost.setCommunityPost(communityPostInformation));
                }catch (Exception exception){
                    exception.printStackTrace();
                    result.setError("存储图片失败");
                    result.setState("defeated");

                    //文件存储失败，回复原有文件
                    if(newFile1.exists()){
                        FileSystemUtils.deleteRecursively(newFile0);
                        newFile1.renameTo(newFile0);
                    }
                }


            }
            AttcIfmt attcIfmt = new AttcIfmt();

            if(result.getError().length() == 0){
                attcIfmt.setImages(String.join(",",listPath));
                attcIfmt.setName(jsonValueClass.getName());
                attcIfmt.setStudentId(jsonValueClass.getStudentID());
                attcIfmt.setOtherInformation(jsonValueClass.getCollegeToClass());
                attcIfmt.setU_id(jsonValueClass.getU_id());

                //删除备份图片文件夹
                if(newFile1.exists()){
                    FileSystemUtils.deleteRecursively(newFile1);
                }

                sAuthentication.sUpdAttcIfmt(attcIfmt);
            }

        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);
    }

    //用户获取认证审核状态
    @RequestMapping("getCertificationStatus")
    public JSONObject getCertificationStatus(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/){
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        UId uId = JSONObject.toJavaObject(jsonObject1, UId.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setState("sKey错误");
        }else {
            if(examine.exUId(Integer.parseInt(uId.getU_id()))){
                result.setError("请检查u_id");
                result.setState("defeated");
            }
        }

        JSONObject resultJson = Tool.classToJson(result);

        if(result.getError().length()==0){
            resultJson.put("information",sAuthentication.getCertificationStatus(uId.getU_id()));
        }else {
            resultJson.put("information",null);
        }

        Information information = new Information();
        information.setData(resultJson);

        return Tool.classToJson(information);
    }

    //工作人员获取用户认证信息
    @RequestMapping("getAttcIfmt")
    public JSONObject getAttcIfmt(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        Integer num = jsonObject1.get("num") instanceof Integer ? ((int) jsonObject1.get("num")) : null;

        JSONObject resultJson = new JSONObject();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            resultJson.put("state","sKey错误");
        }else {
            if (num == null){
                resultJson.put("state","请检查num");
            }else {
                resultJson.put("state","success");
                resultJson.put("attcIfmt",sAuthentication.getAttcIfmt(num,resultJson));
            }
        }

        Information information = new Information();
        information.setData(jsonObject);

        return  Tool.classToJson(information);

    }
    //工作人员写入审核用户认证信息结果
    @RequestMapping("auditAttcIfmt")
    public JSONObject auditAttcIfmt(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/) {
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        AttcIfmt attcIfmt =  JSONObject.toJavaObject(jsonObject1, AttcIfmt.class);


        JSONObject resultJson = new JSONObject();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            resultJson.put("state","sKey错误");
        }else {
            if (attcIfmt.getAuditStatus() == null){
                resultJson.put("state","请检查auditStatus");
            }else{
                if(attcIfmt.getAuditStatus().equals("true")){
                    attcIfmt.setAuditStatus("2");
                }else{
                    attcIfmt.setAuditStatus("0");
                }
            }
            if(attcIfmt.getStatements() == null) {
                resultJson.put("state","statements");
            }
            if(examine.exUId(Integer.parseInt(attcIfmt.getU_id()))){
                resultJson.put("state","请检查u_id");
            }

            if(resultJson.get("state")==null){
                sAuthentication.auditAttcIfmt(attcIfmt);
                resultJson.put("state","success");
            }
        }


        Information information = new Information();
        information.setData(resultJson);

        return  Tool.classToJson(information);

    }
}
