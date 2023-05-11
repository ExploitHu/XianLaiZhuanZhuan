package biao.community.information.port3_6;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class DealInformation {

    String g_id;
    float g_price;
    boolean g_shield;
    boolean g_anonymous;
    String g_information;
    String g_transport;
    Object g_image;
    JSONObject user;
    String u_id;
    int g_view;
    boolean isCollect;
    float recommendValue;
    String g_time;

    public String getG_time() {
        return g_time;
    }

    public void setG_time(String g_time) {
        this.g_time = g_time;
    }

    public float getRecommendValue() {
        return recommendValue;
    }

    public void setRecommendValue(float recommendValue) {
        this.recommendValue = recommendValue;
    }

    public int getG_view() {
        return g_view;
    }

    public void setG_view(int g_view) {
        this.g_view = g_view;
    }

    public boolean isisCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_id() {
        return u_id;
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public float getG_price() {
        return g_price;
    }

    public void setG_price(float g_price) {
        this.g_price = g_price;
    }

    public boolean isG_shield() {
        return g_shield;
    }

    public void setG_shield(boolean g_shield) {
        this.g_shield = g_shield;
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

    public JSONObject getUser() {
        return user;
    }

    public void setUser(JSONObject user) {
        this.user = user;
    }
}
