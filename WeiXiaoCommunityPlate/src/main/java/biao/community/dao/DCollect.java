package biao.community.dao;


import biao.community.information.port2_12and3_11.JsonValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DCollect {

    //增加社区收藏记录
    void bAddCollect(JsonValue jsonValueClass);

    //增加交易收藏记录
    void gAddCollect(JsonValue jsonValueClass);

    void bDelCollect(JsonValue jsonValueClass);

    void gDelCollect(JsonValue jsonValueClass);

}
