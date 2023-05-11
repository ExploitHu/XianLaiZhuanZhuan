package biao.community.information.port2_14and3_14;

public class AttCDInformation {
    String bt_id;
    String bs_id;
    Object bt_image;
    String bt_reply;
    String bt_contents;
    String bt_like;

    String bt_time;

    Object user;
    boolean isLiked;
    boolean isCollect;

    public boolean isisCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public String getBt_id() {
        return bt_id;
    }

    public void setBt_id(String bt_id) {
        this.bt_id = bt_id;
    }

    public String getBs_id() {
        return bs_id;
    }

    public void setBs_id(String bs_id) {
        this.bs_id = bs_id;
    }

    public Object getBt_image() {
        return bt_image;
    }

    public void setBt_image(Object bt_image) {
        this.bt_image = bt_image;
    }

    public String getBt_reply() {
        return bt_reply;
    }

    public void setBt_reply(String bt_reply) {
        this.bt_reply = bt_reply;
    }

    public String getBt_contents() {
        return bt_contents;
    }

    public void setBt_contents(String bt_contents) {
        this.bt_contents = bt_contents;
    }

    public String getBt_time() {
        return bt_time;
    }

    public void setBt_time(String bt_time) {
        this.bt_time = bt_time;
    }

    public String getBt_like() {
        return bt_like;
    }

    public void setBt_like(String bt_like) {
        this.bt_like = bt_like;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public boolean isisLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
