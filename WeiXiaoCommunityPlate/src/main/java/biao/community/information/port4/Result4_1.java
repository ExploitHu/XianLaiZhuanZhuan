package biao.community.information.port4;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Result4_1 {

    List<JSONObject> friendList;
    String error = "";

    public String getError() {
        return error;
    }

    public List<JSONObject> getFriendList() {
        return friendList;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setFriendList(List<JSONObject> friendList) {
        this.friendList = friendList;
    }
}
