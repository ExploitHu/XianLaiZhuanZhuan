package biao.community.information.port3_6;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Result {
    int code = 200;
    String errorMessage = "success";
    List<JSONObject> goodList;
    String slm;

    public void setSlm(String slm) {
        this.slm = slm;
    }

    public String getSlm() {
        return slm;
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

    public List<JSONObject> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<JSONObject> goodList) {
        this.goodList = goodList;
    }
}
