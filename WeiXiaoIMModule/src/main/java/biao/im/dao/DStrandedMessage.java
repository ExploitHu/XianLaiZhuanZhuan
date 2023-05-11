package biao.im.dao;

import biao.im.message.StrandedMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DStrandedMessage {

    //获取用户不在线时收到的消息
    List<StrandedMessage> getStrandedMessage(int u_id);

    //写入滞留消息
    void setStrandedMessage(StrandedMessage strandedMessage);

    //删除滞留消息
    void deleteStrandedMessage(String md5);

    //查找社区帖子发帖人
    String getBuId(String bt_id);
    //查找交易帖子发帖人
    String getGuId(String g_id);
    //检查u_id是否存在
    boolean exUId(String u_id);

    //获取社区模块未读消息数量
    int getBTobeRead(String u_id);

    //获取商城模块未读消息数量
    int getGTobeRead(String u_id);

}
