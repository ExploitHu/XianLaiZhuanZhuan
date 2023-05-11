package biao.community.dao;


import biao.community.information.port2_1and3_1.CommunityPostInformation;
import biao.community.information.port2_13and3_12.DealInformation;
import biao.community.information.port2_13and3_12.JsonValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DGetUserPost {

    List<CommunityPostInformation> getUserBPost(JsonValue jsonValueClass);

    List<DealInformation> getUserGPost(JsonValue jsonValueClass);

    boolean isBCollect(String bt_id,String u_id);

    boolean isGCollect(String g_id,String u_id);
}
