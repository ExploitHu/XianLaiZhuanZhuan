package biao.community.service;

import biao.community.dao.DAuthentication;
import biao.community.information.port5_6__9.AttcIfmt;
import biao.community.information.port5_6__9.CertificationStatus;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class SAuthentication {

    @Autowired
    DAuthentication dAuthentication;

    //写入用户认证信息
    public void sUpdAttcIfmt(AttcIfmt attcIfmt){

        //删除原有的
        dAuthentication.deleteOld(Integer.parseInt(attcIfmt.getU_id()));

        dAuthentication.updAttcIfmt(attcIfmt);
    }

    //获取用户认证状态
    public JSONObject getCertificationStatus(String u_id){

        CertificationStatus certificationStatus = dAuthentication.getCertificationStatus(u_id);

        if(certificationStatus == null){
            certificationStatus = new CertificationStatus();
            certificationStatus.setAuditStatus("待上传");

        }else{
            switch (certificationStatus.getAuditStatus()){
                case "0":certificationStatus.setAuditStatus("不通过");
                    break;
                case "1":certificationStatus.setAuditStatus("待审核");
                    break;
                case "2":certificationStatus.setAuditStatus("通过");
                    break;
            }
        }

        return Tool.classToJson(certificationStatus);
    }

    public JSONObject getAttcIfmt(int num,JSONObject resultJson){
        JSONObject attcIfmtJson = Tool.classToJson(dAuthentication.getAttcIfmt(num));
        if(attcIfmtJson==null){
            resultJson.put("num",-1);
            return null;
        }

        List<String> listimage = Arrays.asList(attcIfmtJson.get("images").toString().split(","));
        if(listimage!= null){
            attcIfmtJson.put("images",listimage);
        }
        attcIfmtJson.remove("otherInformation");
        resultJson.put("num",attcIfmtJson.get("num"));
        attcIfmtJson.remove("num");

        /*
        switch ((int)attcIfmtJson.get("auditStatus")){
            case 1:
                attcIfmtJson.put("auditStatus","待审");
                break;
            case 2:
                attcIfmtJson.put("auditStatus","通过");
                break;
            case 0:
                attcIfmtJson.put("auditStatus","不通过");
                break;
            default:
                attcIfmtJson.put("auditStatus","???");
        }

         */
        attcIfmtJson.put("auditStatus","待审");

        return attcIfmtJson;
    }

    public void auditAttcIfmt(AttcIfmt attcIfmt){

        dAuthentication.auditAttcIfmt(attcIfmt.getU_id(),attcIfmt.getAuditStatus(),attcIfmt.getStatements());
        if(attcIfmt.getAuditStatus().equals("2")){
            dAuthentication.updateUIdentity(attcIfmt.getU_id(),attcIfmt.getStatements(),attcIfmt.getStudentId());
        }
    }


}
