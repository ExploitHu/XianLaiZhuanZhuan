package biao.community.information.port2_1and3_1;

import com.alibaba.fastjson.JSONObject;

public class CommunityPostInformation {

    String bt_id;
    String u_id;
    String bt_like;
    String bt_reply;
    String bs_id;
    String bt_contents;
    JSONObject bt_time;
    Object bt_image;
    float recommendValue;
    //
    boolean isLike;
    boolean isBooked;
    //
    Object user;
    String bt_time_Temp;

    boolean b_anonymous;

    boolean isCollect;

    public boolean isisCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public float getRecommendValue() {
        return recommendValue;
    }

    public void setRecommendValue(float recommendValue) {
        this.recommendValue = recommendValue;
    }

    public void setB_anonymous(boolean b_anonymous) { this.b_anonymous = b_anonymous; }

    public boolean getB_anonymous(){ return this.b_anonymous; }

    public void setIsLike(boolean isLiked) {
        this.isLike = isLiked;
    }

    public boolean getIsLike() {
        return isLike;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public boolean getIsBooked() {
        return isBooked;
    }


    public String getBt_time_Temp() {
        return bt_time_Temp;
    }

    public void setBt_time_Temp(String bt_time_Temp) {
        this.bt_time_Temp = bt_time_Temp;
    }

    public void setBt_time(JSONObject bt_time) {
        this.bt_time = bt_time;
    }

    public void setUser(Object user) {
        this.user = user;
    }


    public String getBt_id() {
        return bt_id;
    }

    public String getU_id() {
        return u_id;
    }

    public String getBt_like() {
        return bt_like;
    }

    public String getBt_reply() {
        return bt_reply;
    }

    public String getBs_id() {
        return bs_id;
    }

    public String getBt_contents() {
        return bt_contents;
    }

    public Object getBt_time() {
        return bt_time;
    }

    public Object getBt_image() {
        return bt_image;
    }

    public void setBt_image(Object bt_image) {
        this.bt_image = bt_image;
    }

    public Object getUser() {
        return user;
    }



}
