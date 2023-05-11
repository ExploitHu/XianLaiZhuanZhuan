package biao.community.dao;

import biao.community.information.port3_5.JsonValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DSetDealPost {
    //创建一个信息的二手交易帖子
    void setDealPost(JsonValue jsonValueCalss);
}
