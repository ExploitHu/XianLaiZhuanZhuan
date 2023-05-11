package biao.community.dao;

import biao.community.information.port2_17and3_16.BTobeComment;
import biao.community.information.port2_17and3_16.GTobeComment;
import biao.community.information.port2_3.BMainComment;
import biao.community.information.port2_3.BSonComment;
import biao.community.information.port2_3.GMainComment;
import biao.community.information.port2_3.GSonComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DGetComment {

    //获取社区帖子获取主评论
    List<BMainComment> getBMainComment(int bt_id);

    //获取社区帖子子评论
    List<BSonComment> getBSonComment(String btc_id,String bt_id);

    //获取交易帖子获取主评论
    List<GMainComment> getGMainComment(int g_id);

    //获取交易帖子子评论
    List<GSonComment> getGSonComment(String gc_id,String g_id);

    //获取用户收到的社区帖子未读评论（被@的）
    List<BTobeComment> getBToBeRead(String u_id,String id,int l);

    //获取用户收到的商城帖子未读评论（被@的）
    List<GTobeComment> getGToBeRead(String u_id,String id,int l);


    //查询用户社区未读消息条数
    int getBTobeCommentNumber(int u_id);

    //查询用户商城未读消息条数
    int getGTobeCommentNumber(int u_id);

    //重置用户社区未读消息条数
    void resetBTobeComment(String u_id);

    //重置用户商城未读消息条数
    void resetGTobeComment(String u_id);
}
