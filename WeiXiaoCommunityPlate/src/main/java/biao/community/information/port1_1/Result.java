package biao.community.information.port1_1;

import com.alibaba.fastjson.JSONObject;
import org.springframework.lang.Nullable;

public class Result {
    JSONObject user;
    int code;
    String errorMessage = "success";


    public Result() {
    }

    public Result(JSONObject user, int code, @Nullable String errorMessage) {
        this.user = user;
        this.code = code;
        if (errorMessage != null) {
            this.errorMessage = errorMessage;
        }
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public JSONObject getUser() {
        return user;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setUser(JSONObject user) {
        this.user = user;
    }

}
