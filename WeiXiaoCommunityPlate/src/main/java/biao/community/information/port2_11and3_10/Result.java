package biao.community.information.port2_11and3_10;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Result {

    List<JSONObject> collect;

    String error = "";

    public List<JSONObject> getCollect() {
        return collect;
    }

    public void setCollect(List<JSONObject> collect) {
        this.collect = collect;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
