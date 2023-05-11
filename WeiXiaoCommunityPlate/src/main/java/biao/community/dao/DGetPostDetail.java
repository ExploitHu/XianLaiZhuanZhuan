package biao.community.dao;

import biao.community.information.port2_1and3_1.CommunityPostInformation;
import biao.community.information.port3_2.GDInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DGetPostDetail {

    //获取交易帖子详细信息
    GDInformation getGPostetail(String g_id);

    //获取交易帖子详细信息
    CommunityPostInformation getBPostetail(String bt_id);

    //用户是否对商品帖子进行点赞
    boolean isLike(String g_id,String u_id);

    //是否收藏
    boolean isGCollect(String g_id,String u_id);
}
