package biao.community.information.port2_13and3_12;

public class DealInformation {

    String g_id;
    double g_price;
    boolean g_shield;
    Object user;
    boolean g_anonymous;
    String g_information;
    String g_transport;
    Object g_image;
    String u_id;
    String g_time;
    boolean isCollect;
    boolean isLike;

    public boolean isisLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public boolean isisCollect() {
        return isCollect;
    }

    public String getG_time() {
        return g_time;
    }

    public void setG_time(String g_time) {
        this.g_time = g_time;
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public double getG_price() {
        return g_price;
    }

    public void setG_price(double g_price) {
        this.g_price = g_price;
    }

    public boolean isG_shield() {
        return g_shield;
    }

    public void setG_shield(boolean g_shield) {
        this.g_shield = g_shield;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public boolean isG_anonymous() {
        return g_anonymous;
    }

    public void setG_anonymous(boolean g_anonymous) {
        this.g_anonymous = g_anonymous;
    }

    public String getG_information() {
        return g_information;
    }

    public void setG_information(String g_information) {
        this.g_information = g_information;
    }

    public String getG_transport() {
        return g_transport;
    }

    public void setG_transport(String g_transport) {
        this.g_transport = g_transport;
    }

    public Object getG_image() {
        return g_image;
    }

    public void setG_image(Object g_image) {
        this.g_image = g_image;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }
}
