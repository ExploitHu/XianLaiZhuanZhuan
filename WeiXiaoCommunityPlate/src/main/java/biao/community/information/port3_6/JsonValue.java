package biao.community.information.port3_6;

public class JsonValue {
    String u_id;
    String Key;

    int size;
    String time;
    float recommendValue;
    String slm;
    float minPrice;
    float maxPrice;
    String g_class;
    String u_city;


    public String getTime() {
        return time;
    }

    public String getSlm() {
        return slm;
    }

    public float getRecommendValue() {
        return recommendValue;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSlm(String slm) {
        this.slm = slm;
    }

    public void setRecommendValue(float recommendValue) {
        this.recommendValue = recommendValue;
    }

    public void setU_city(String u_city) {
        this.u_city = u_city;
    }

    public void setG_class(String g_class) {
        this.g_class = g_class;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }


    public String getU_city() {
        return u_city;
    }

    public String getG_class() {
        return g_class;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
