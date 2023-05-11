package biao.community.dao;


import biao.community.information.port1_2.AnonPersonalMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DGetAnonPersonalInformation {
    //获取匿名用户用户信息
    AnonPersonalMessage getAnonPersonalInformation(int ua_id);
}
