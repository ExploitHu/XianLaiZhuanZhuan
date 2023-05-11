package biao.community.dao;

import biao.community.information.port4_3.JsonValue;
import biao.community.information.port4_3.OUInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DSearchOtherUser {

    //查找人
    OUInformation searchOtherUser(String u_id);

    //是否为好友关系
    boolean isFriend(JsonValue jsonValue);
}
