package biao.community.information.port3_2;

import java.util.List;

public class GDInformation {

    private String g_id;
    private Integer g_price;
    private Boolean g_shield;
    private String g_information;
    private String g_transport;
    private Object g_image;
    private String g_class;
    private Integer g_view;
    private String g_time;
    private String u_id;
    private Object user;
    private boolean g_anonymous;
    private boolean isBooked;

    private int g_like;
    private boolean isLike;
    private boolean isCollect;

    public boolean isG_anonymous() {
        return g_anonymous;
    }

    public boolean isisBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public void setG_anonymous(boolean g_anonymous) {
        this.g_anonymous = g_anonymous;
    }

    public boolean isisCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean isisLike() {
        return isLike;
    }

    public int getG_like() {
        return g_like;
    }

    public void setG_like(int g_like) {
        this.g_like = g_like;
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

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public Integer getG_price() {
        return g_price;
    }

    public void setG_price(Integer g_price) {
        this.g_price = g_price;
    }

    public Boolean getG_shield() {
        return g_shield;
    }

    public void setG_shield(Boolean g_shield) {
        this.g_shield = g_shield;
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

    public String getG_class() {
        return g_class;
    }

    public void setG_class(String g_class) {
        this.g_class = g_class;
    }

    public Integer getG_view() {
        return g_view;
    }

    public void setG_view(Integer g_view) {
        this.g_view = g_view;
    }

    public String getG_time() {
        return g_time;
    }

    public void setG_time(String g_time) {
        this.g_time = g_time;
    }
}
