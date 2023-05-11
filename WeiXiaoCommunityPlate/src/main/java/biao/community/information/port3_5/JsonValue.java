package biao.community.information.port3_5;

public class JsonValue {
    int g_id;
    String u_id;
    String g_class;
    String g_information;
    float g_price;
    boolean g_transport;
    boolean g_anonymous;

    String g_image;

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public int getG_id() {
        return g_id;
    }

    public String getG_image() {
        return g_image;
    }

    public void setG_image(String g_image) {
        this.g_image = g_image;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getG_class() {
        return g_class;
    }

    public void setG_class(String g_class) {
        this.g_class = g_class;
    }

    public String getG_information() {
        return g_information;
    }

    public void setG_information(String g_information) {
        this.g_information = g_information;
    }

    public float getG_price() {
        return g_price;
    }

    public void setG_price(float g_price) {
        this.g_price = g_price;
    }

    public boolean getG_transport() {
        return g_transport;
    }

    public void setG_transport(boolean g_transport) {
        this.g_transport = g_transport;
    }

    public boolean getG_anonymous() {
        return g_anonymous;
    }

    public void setG_anonymous(boolean b_anonymous) {
        this.g_anonymous = b_anonymous;
    }
}
