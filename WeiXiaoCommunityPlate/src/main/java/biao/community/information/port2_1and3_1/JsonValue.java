package biao.community.information.port2_1and3_1;

public class JsonValue {
    String u_id;
    int sum;
    String bs_id;
    String g_class;
    //station location marker 位置标识
    String slm = "";

    String u_city;

    String time = "now";

    float recommendValue = -499.999f;


    public void setU_city(String u_city) {
        this.u_city = u_city;
    }

    public String getU_city() {
        return u_city;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getBs_id() {
        return bs_id;
    }

    public String getG_class() {
        return g_class;
    }

    public void setBs_id(String bs_id) {
        this.bs_id = bs_id;
    }

    public void setG_class(String g_class) {
        this.g_class = g_class;
    }

    public String getSlm() {
        return slm;
    }

    public void setSlm(String slm) {
        this.slm = slm;
    }

    public float getRecommendValue() {
        return recommendValue;
    }

    public void setRecommendValue(float recommendValue) {
        this.recommendValue = recommendValue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
