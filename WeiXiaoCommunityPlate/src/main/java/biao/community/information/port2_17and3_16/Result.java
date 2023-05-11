package biao.community.information.port2_17and3_16;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Result {
    List<JSONObject> commentList;
    String state = "success";
    String slm;

    public void setSlm(String slm) {
        this.slm = slm;
    }

    public String getSlm() {
        return slm;
    }

    public String getState() {
        return state;
    }

    public List<JSONObject> getCommentList() {
        return commentList;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCommentList(List<JSONObject> commentList) {
        this.commentList = commentList;
    }
}
