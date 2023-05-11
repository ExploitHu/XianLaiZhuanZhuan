package biao.community.information.port2_10and3_9;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Result {
    List<JSONObject> history;
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

    public List<JSONObject> getHistory() {
        return history;
    }

    public void setHistory(List<JSONObject> history) {
        this.history = history;
    }
}
