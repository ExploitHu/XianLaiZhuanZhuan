package biao.community.service;


import biao.community.dao.DSetDealPost;
import biao.community.information.port3_5.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SSetDealPost {

    @Autowired
    DSetDealPost dSetDealPost;

    public String setDealPost(JsonValue jsonValueClass){

        dSetDealPost.setDealPost(jsonValueClass);


        return Integer.toString(jsonValueClass.getG_id());
    }
}
