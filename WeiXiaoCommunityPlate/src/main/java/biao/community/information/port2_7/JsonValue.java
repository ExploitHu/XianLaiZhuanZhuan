package biao.community.information.port2_7;

public class JsonValue {

    String u_id;
    String key;
    int size;
    String time;
    float recommendValue;
    String slm;

    String bs_id;
    String u_city;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getRecommendValue() {
        return recommendValue;
    }

    public void setRecommendValue(float recommendValue) {
        this.recommendValue = recommendValue;
    }

    public String getSlm() {
        return slm;
    }

    public void setSlm(String slm) {
        this.slm = slm;
    }

    public String getBs_id() {
        return bs_id;
    }

    public void setBs_id(String bs_id) {
        this.bs_id = bs_id;
    }

    public String getU_city() {
        return u_city;
    }

    public void setU_city(String u_city) {
        this.u_city = u_city;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
