package biao.community.service;

import biao.community.dao.DFriends;
import biao.community.information.port4.AddFriend;
import biao.community.information.port4.FriendInformation;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SFriends {

    @Autowired
    DFriends dFriends;

    //获取好友信息列表
    public List<JSONObject> getFriends(int u_id){
    //public void getFriends(int u_id){
        List<FriendInformation> listFriends = dFriends.getFriends(u_id);

        List<JSONObject> list = new ArrayList<JSONObject>();

        for (int i = 0 ; i< listFriends.size(); i++){
            list.add(Tool.classToJson(listFriends.get(i)));
        }
        return list;
    }

    //4.2用户新增关注人（新增好友）
    public void addFriend(AddFriend addFriend){
        System.out.println("增加");
        dFriends.addFriend(addFriend);
    }

    public void delFriend(AddFriend addFriend){
        dFriends.delFriend(addFriend);
    }

}
