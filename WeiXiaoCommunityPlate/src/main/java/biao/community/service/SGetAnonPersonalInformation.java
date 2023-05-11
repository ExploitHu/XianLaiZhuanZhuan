package biao.community.service;

import biao.community.dao.DGetAnonPersonalInformation;
import biao.community.information.port1_2.AnonPersonalId;
import biao.community.information.port1_2.AnonPersonalMessage;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SGetAnonPersonalInformation {

    @Autowired
    DGetAnonPersonalInformation dGetAnonPersonalInformation;

    //获取匿名用户信息
    public JSONObject getAnonPersonalInformation(AnonPersonalId anonPersonalId) {
       AnonPersonalMessage anonPersonalMessage = dGetAnonPersonalInformation.getAnonPersonalInformation(Integer.parseInt(anonPersonalId.getUa_id()));

        return Tool.classToJson(anonPersonalMessage);
    }

}
