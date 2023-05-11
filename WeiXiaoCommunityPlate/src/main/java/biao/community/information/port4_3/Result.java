package biao.community.information.port4_3;

import com.alibaba.fastjson.JSONObject;

public class Result {

    JSONObject information;
    String state = "success";

    public JSONObject getInformation() {
        return information;
    }

    public void setInformation(JSONObject information) {
        this.information = information;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
