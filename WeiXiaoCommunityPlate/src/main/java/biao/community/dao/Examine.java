package biao.community.dao;


import biao.community.TestStruct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Examine {
    //检查u_id  不正确返回true
    boolean exUId(int u_id);

    //检查bs_id  不正确返回true
    boolean exBsId(String bs_id);

    //检查g_class
    boolean exGClass(String name);

    //检查g_id
    boolean exGId(int g_id);

    //检查bt_id
    boolean exBtId(int bt_id);

    //检查校区
    boolean exCity(String city);

    //检查用户是否发布了该社区帖子
    boolean UserIssueBPost(String bt_id,String u_id);

    //检查用户是否发布了该交易帖子
    boolean UserIssueGPost(String g_id,String u_id);

    //测试查询
    List<TestStruct> test(String u_id);

}
