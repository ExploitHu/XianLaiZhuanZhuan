package biao.community.information.port2_1and3_1;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Result {
    List<JSONObject> post;
    int code;
    //station location marker 位置标识
    String slm;
    String errorMessage = "success";

    public String getSlm() {
        return slm;
    }

    public void setSlm(String slm) {
        this.slm = slm;
    }

    public void setPost(List<JSONObject> post) {
        this.post = post;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<JSONObject> getPost() {
        return post;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
