package biao.community.dao;

import biao.community.information.port4.AddFriend;
import biao.community.information.port4.FriendInformation;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DFriends {

    //获取用户好友列表
    List<FriendInformation> getFriends(int u_id);

    //4.2用户新增关注人（新增好友）
    void addFriend(AddFriend addFriend);

    //取消关注（删除好友）
    void delFriend(AddFriend addFriend);
}
