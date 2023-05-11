package biao.community.dao;

import biao.community.information.port2_9and3_8.JsonValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DAddHistory {

    //设置社区帖子记录
    void bAddHistory(JsonValue jsonValueClass);

    //删除社区帖子记录
    void bDeleteHistory(JsonValue jsonValueClass);

    //保留n条，删除多余的记录（删除时间最早的）
    void bDeleteBeyond(int n,int u_id);

    //设置交易帖子记录
    void gAddHistory(JsonValue jsonValueClass);

    //删除社区帖子记录
    void gDeleteHistory(JsonValue jsonValueClass);

    //保留n条，删除多余的记录（删除时间最早的）
    void gDeleteBeyond(int n,int u_id);

    //增加社区帖子浏览数（点击量）
    void addBView(String bt_id);

    //增加商品帖子浏览数（点击量）
    void addGView(String g_id);
}
