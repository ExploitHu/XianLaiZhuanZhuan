package biao.community.dao;

import biao.community.information.port1_1.JsonValue;
import biao.community.information.port1_1.UserInformation;
import biao.community.information.port1_4.UserIfmt;
import biao.community.information.port5_6__9.AttcIfmt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DGetPersonalInformation {

    UserInformation getInformation(int id);

    int getUserCreditScore(String u_id);

    //是否关注
    boolean isBook(JsonValue jsonValueClass);

    //获取用户头像地址
    String getUserHead(String u_id);

    //更新用户头像地址
    void updateUHead(String u_id,String u_head);

    //update昵称
    void updateUNickname(String u_id,String u_nickname);

    //更改Tel（电话）
    void updateUTel (String u_id,String u_tel);

    //更改性别
    void updateUSex (String u_id,String u_sex);

    //更改个人说明
    void updateULike (String u_id,String u_like);

    //更改地址
    void updateUAddress (String u_id,String u_address);

    //更改生日
    void updateUBirth (String u_id,String u_birth);

    //获取更新后的用户信息
    UserIfmt getUserIfmt(String u_id);


}


