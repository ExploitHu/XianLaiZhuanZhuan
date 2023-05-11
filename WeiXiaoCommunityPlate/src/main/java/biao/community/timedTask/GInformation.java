package biao.community.timedTask;

public class GInformation {
    int g_id;
    int g_view;
    int g_like;
    String 	g_time;

    float recommendValue;

    public int getG_like() {
        return g_like;
    }

    public void setG_like(int g_like) {
        this.g_like = g_like;
    }

    public float getRecommendValue() {
        return recommendValue;
    }

    public void setRecommendValue(float recommendValue) {
        this.recommendValue = recommendValue;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public int getG_view() {
        return g_view;
    }

    public void setG_view(int g_view) {
        this.g_view = g_view;
    }

    public String getG_time() {
        return g_time;
    }

    public void setG_time(String g_time) {
        this.g_time = g_time;
    }
}
