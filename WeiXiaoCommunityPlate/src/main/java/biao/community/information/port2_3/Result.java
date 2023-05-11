package biao.community.information.port2_3;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Result {

    private String bt_id;
    List<JSONObject> commentList;
    int code;
    String errorMessage = "success";


    public String getBt_id() {
        return bt_id;
    }

    public void setBt_id(String bt_id) {
        this.bt_id = bt_id;
    }

    public List<JSONObject> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<JSONObject> commentList) {
        this.commentList = commentList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
