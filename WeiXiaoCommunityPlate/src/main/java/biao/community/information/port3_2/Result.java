package biao.community.information.port3_2;

import com.alibaba.fastjson.JSONObject;

public class Result {
    JSONObject detailInformation;
    String errorMessage = "success";
    int code;

    public void setDetailInformation(JSONObject detailInformation) {
        this.detailInformation = detailInformation;
    }

    public JSONObject getDetailInformation() {
        return detailInformation;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
