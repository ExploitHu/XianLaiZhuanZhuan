package biao.community.dao;

import biao.community.information.port2_14and3_14.AttGInformation;
import biao.community.information.port2_1and3_1.CommunityPostInformation;
import biao.community.information.port2_1and3_1.GoodPost;
import biao.community.information.port2_1and3_1.UserInformation5;
import biao.community.information.port2_14and3_14.AttCDInformation;
import biao.community.information.port2_14and3_14.JsonValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DGetPost {
    //获取社区sum个帖子
    //List<CommunityPostInformation> getPlateCommunityDynamic(biao.community.information.port2_1and3_1.JsonValue jsonValueClass);

    //获取sum个帖子
    List<CommunityPostInformation> getCommunityDynamic(biao.community.information.port2_1and3_1.JsonValue jsonValueClass);

    //获取用户简略信息
    UserInformation5 getUserBriefInformation(int u_id);

    //获取匿名用户信息
    UserInformation5 getAnonUserInformation(int u_id);

    //判读用户是否对改帖子点赞
    boolean judgeLike(int u_id,int bt_id);

    //判断用户(u_id)是否关注了指定用户(uf_id)
    boolean judgeBooked(int u_id, int uf_id);

    //查找编号对应的板块名
    String getBsIdName(int bs_id);

    //获取关注人发布的社区帖子
    List<AttCDInformation> getAttCommunityDynamic(JsonValue jsonValueClass);

    //获取bs_id板块sum个帖子
    //List<GoodPost> getGoodDetailC(biao.community.information.port2_1and3_1.JsonValue jsonValueClass);

    //获取交易sum个帖子
    List<GoodPost> getGoodDetail(biao.community.information.port2_1and3_1.JsonValue jsonValueClass);

    //获取bs_id板块sum个帖子
    String getGclassName(int g_class);

    //判断用户是是否对交易帖子点赞
    boolean isGlike(String g_id,String u_id);

    //判断用户是否是否收藏（社区）
    boolean isBCollect(String bt_id,String u_id);

    //判断用户是否是否收藏（交易）
    boolean isGCollect(String g_id,String u_id);

    //获取关注人发布的帖子
    List<AttGInformation> getAttGPost(JsonValue jsonValueClass);

}
