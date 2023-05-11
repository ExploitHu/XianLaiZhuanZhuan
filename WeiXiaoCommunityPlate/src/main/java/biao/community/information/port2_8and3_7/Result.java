package biao.community.information.port2_8and3_7;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Result {
    List<JSONObject> classify;

    public void setClassify(List<JSONObject> classify) {
        this.classify = classify;
    }

    public List<JSONObject> getClassify() {
        return classify;
    }
}
