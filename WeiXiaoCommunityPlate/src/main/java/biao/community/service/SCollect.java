package biao.community.service;


import biao.community.dao.DCollect;
import biao.community.information.port2_12and3_11.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SCollect {

    @Autowired
    DCollect dCollect;

    public void bAddCollect(JsonValue jsonValueClass){
        dCollect.bAddCollect(jsonValueClass);
    }

    public void gAddCollect(JsonValue jsonValueClass){
        dCollect.gAddCollect(jsonValueClass);
    }

    public void bDelCollect(JsonValue jsonValueClass){
        dCollect.bDelCollect(jsonValueClass);
    }

    public void gDelCollect(JsonValue jsonValueClass){
        dCollect.gDelCollect(jsonValueClass);
    }

}
