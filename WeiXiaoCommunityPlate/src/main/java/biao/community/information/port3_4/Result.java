package biao.community.information.port3_4;

import com.alibaba.fastjson.JSONObject;

public class Result {

    private String g_id;
    private String gc_id;
    private String u_id0;
    private String u_id;
    private String gc_com;
    private String gc_time;

    int code = 200;
    String errorMessage = "success";

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public String getGc_id() {
        return gc_id;
    }

    public void setGc_id(String gc_id) {
        this.gc_id = gc_id;
    }

    public String getU_id0() {
        return u_id0;
    }

    public void setU_id0(String u_id0) {
        this.u_id0 = u_id0;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getGc_com() {
        return gc_com;
    }

    public void setGc_com(String gc_com) {
        this.gc_com = gc_com;
    }

    public String getGc_time() {
        return gc_time;
    }

    public void setGc_time(String gc_time) {
        this.gc_time = gc_time;
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
