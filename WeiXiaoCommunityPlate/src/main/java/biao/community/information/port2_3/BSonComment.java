package biao.community.information.port2_3;

public class BSonComment {

    //查询结果
    private String u_id0;
    private String u_id;
    private String btc_com;
    private String btc_time;
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

    public String getU_id() {
        return u_id;
    }

    public String getBtc_com() {
        return btc_com;
    }

    public String getBtc_time() {
        return btc_time;
    }

    public void setBtc_time(String btc_time) {
        this.btc_time = btc_time;
    }
}
