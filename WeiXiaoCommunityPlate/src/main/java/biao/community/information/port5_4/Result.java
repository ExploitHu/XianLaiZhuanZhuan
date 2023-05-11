package biao.community.information.port5_4;

import com.alibaba.fastjson.JSONObject;
import org.springframework.lang.Nullable;

import java.util.List;

public class Result {

    List<JSONObject> campus;
    int code;
    String errorMessage="success";

    public Result(){
    }

    public Result(List<JSONObject> campus, int code, @Nullable String errorMessage) {
        this.campus = campus;
        this.code = code;
        if (errorMessage != null) {
            this.errorMessage = errorMessage;
        }
    }

    public List<JSONObject> getCampus() {
        return campus;
    }

    public void setCampus(List<JSONObject> campus) {
        this.campus = campus;
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
