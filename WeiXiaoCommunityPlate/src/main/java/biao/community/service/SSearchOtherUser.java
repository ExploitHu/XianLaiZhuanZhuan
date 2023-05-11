package biao.community.service;

import biao.community.dao.DSearchOtherUser;
import biao.community.information.port4_3.JsonValue;
import biao.community.information.port4_3.OUInformation;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SSearchOtherUser {

    @Autowired
    DSearchOtherUser dSearchOtherUser;

    public JSONObject searchOtherUser(JsonValue jsonValueClass){

        OUInformation ouInformation = dSearchOtherUser.searchOtherUser(jsonValueClass.getSearchUser());
        ouInformation.setFriend(dSearchOtherUser.isFriend(jsonValueClass));

        return Tool.classToJson(ouInformation);

    }

}
