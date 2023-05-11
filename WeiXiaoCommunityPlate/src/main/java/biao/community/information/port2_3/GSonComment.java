package biao.community.information.port2_3;

public class GSonComment {
    //查询结果
    private String u_id0;
    private String u_id;
    private String gc_com;
    private String gc_time;
    private String u_head;
    private String u_nickname;

    public String getU_nickname() {
        return u_nickname;
    }

    public String getU_head() {
        return u_head;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public void setU_head(String u_head) {
        this.u_head = u_head;
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
}
