package biao.community.service;


import biao.community.dao.DAddHistory;
import biao.community.information.port2_9and3_8.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAddHistory {

    @Autowired
    DAddHistory dAddHistory;

    public void bAddHistory(JsonValue jsonValueClass){
        //去重（删除以前重复的历史记录）
        dAddHistory.bDeleteHistory(jsonValueClass);

        dAddHistory.bAddHistory(jsonValueClass);

        dAddHistory.addBView(jsonValueClass.getBg_id());

        //只保留100条记录
        dAddHistory.bDeleteBeyond(100,Integer.parseInt(jsonValueClass.getU_id()));

    }

    public void gAddHistory(JsonValue jsonValueClass){
        //去重（删除以前重复的历史记录）
        dAddHistory.gDeleteHistory(jsonValueClass);

        dAddHistory.gAddHistory(jsonValueClass);

        dAddHistory.addGView(jsonValueClass.getBg_id());

        //只保留100条记录
        dAddHistory.gDeleteBeyond(100,Integer.parseInt(jsonValueClass.getU_id()));
    }
}
