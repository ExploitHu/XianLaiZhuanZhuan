package biao.community.dao;

import biao.community.information.port5_5.PassAndUId;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DLogIn {

    //通过u_email获取用户密码
    PassAndUId eGetPassworld(String u_email);

    //通过u_id获取用户密码
    PassAndUId uGetPassworld(String u_id);

}
