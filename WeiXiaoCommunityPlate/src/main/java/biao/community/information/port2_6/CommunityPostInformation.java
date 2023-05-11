package biao.community.information.port2_6;

public class CommunityPostInformation {
    int bt_id;
    String bs_id;
    String u_id;
    String bt_contents;
    String bt_image;
    boolean b_anonymous;

    public int getBt_id() {
        return bt_id;
    }

    public void setBt_id(int bt_id) {
        this.bt_id = bt_id;
    }

    public String getBs_id() {
        return bs_id;
    }

    public void setBs_id(String bs_id) {
        this.bs_id = bs_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getBt_contents() {
        return bt_contents;
    }

    public void setBt_contents(String bt_contents) {
        this.bt_contents = bt_contents;
    }

    public String getBt_image() {
        return bt_image;
    }

    public void setBt_image(String bt_image) {
        this.bt_image = bt_image;
    }

    public boolean getB_anonymous() {
        return b_anonymous;
    }

    public void setB_anonymous(boolean b_anonymous) {
        this.b_anonymous = b_anonymous;
    }
}
