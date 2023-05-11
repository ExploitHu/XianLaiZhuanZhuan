package biao.community.timedTask;

public class BInformation {
    int bt_id;
    int bt_click;
    String 	bt_time;
    int bt_like;

    float recommendValue;

    public int getBt_id() {
        return bt_id;
    }

    public void setBt_id(int bt_id) {
        this.bt_id = bt_id;
    }

    public int getBt_click() {
        return bt_click;
    }

    public void setBt_click(int bt_click) {
        this.bt_click = bt_click;
    }

    public String getBt_time() {
        return bt_time;
    }

    public void setBt_time(String bt_time) {
        this.bt_time = bt_time;
    }

    public int getBt_like() {
        return bt_like;
    }

    public void setBt_like(int bt_like) {
        this.bt_like = bt_like;
    }

    public float getRecommendValue() {
        return recommendValue;
    }

    public void setRecommendValue(float recommendValue) {
        this.recommendValue = recommendValue;
    }
}
