package biao.community.dao;


import biao.community.information.port2_6.CommunityPostInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DSetCommunityPost {

    //新增社区帖子信息
    //int setCommunityPost(String bs_id,String u_id,String bt_contents,String bt_image,boolean b_anonymous);
    int setCommunityPost(CommunityPostInformation information);

    //
}
