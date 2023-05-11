package biao.community.information.port2_3;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class GMainComment {
    //查询结果
    private String gc_id;
    private String u_id0;
    private String gc_com;
    private String gc_time;
    private String u_head;
    private String u_nickname;

    public String getU_head() {
        return u_head;
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_head(String u_head) {
        this.u_head = u_head;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    //处理结果  根据btc_id and u_id!=null
    private List<JSONObject> sonComment;

    public List<JSONObject> getSonComment() {
        return sonComment;
    }

    public void setSonComment(List<JSONObject> sonComment) {
        this.sonComment = sonComment;
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
}
