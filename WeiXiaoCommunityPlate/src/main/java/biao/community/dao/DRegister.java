package biao.community.dao;

import biao.community.information.port5_1.UpdatePass;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DRegister {
    //新增用户
    void establishUserMessage(int u_id, String u_email, String u_pass, String u_city, String u_birth, String u_sex, String u_nickname, String u_tel, String u_name, String u_num, String u_regdate,String u_head);

    //新增对应匿名信息
    void establishAnonUserMessage(int u_id,String ua_sex);

    //查询区间最小空缺值
    int minimumIntervalVacancyValue(int min, int max);

    //查询数字对应的校区中文名
    String getCampus(int campusCode);

    //检查校区代码是否正确
    boolean examineCity(int u_city);

    //检查邮箱是否充分注册
    boolean checkEmail(String u_email);

    //更新用户密码
    void updatePass(UpdatePass updatePass);

}
