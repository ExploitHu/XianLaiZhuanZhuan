package biao.community.information.port2_13and3_12;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Result {
    String u_id;
    List<JSONObject> post;
    String time;
    String error = "";

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getU_id() {
        return u_id;
    }

    public List<JSONObject> getPost() {
        return post;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public void setPost(List<JSONObject> post) {
        this.post = post;
    }
}
