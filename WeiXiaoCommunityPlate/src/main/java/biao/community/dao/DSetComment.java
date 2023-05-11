package biao.community.dao;

import org.apache.ibatis.annotations.Mapper;
import biao.community.information.port2_5.Result;

@Mapper
public interface DSetComment {
    //获取评论表最小空缺值（gc_id）
    int getMinGapvalue(int bt_id);

    //设置社区帖子主评论
    void setMainComment(Result result);

    //设置社区帖子子评论
    void setSonComment(Result result);

    //查询时间
    String selectBtcTime(String id);

    //被评论人社区待浏览评论条数+1
    void bReadAnd1(String u_id,String bt_id,String u_id0);



    //获取评论表最小空缺值（gc_id）
    int getGMinGapvalue(int g_id);

    //设置商品帖子主评论
    void setGMainComment(biao.community.information.port3_4.Result result);

    //设置商品帖子子评论
    void setGSonComment(biao.community.information.port3_4.Result result);

    //查询时间
    String selectGcTime(String id);

    //被评论人商城待浏览评论条数+1
    void gReadAnd1(String u_id,String g_id,String u_id0);

}
