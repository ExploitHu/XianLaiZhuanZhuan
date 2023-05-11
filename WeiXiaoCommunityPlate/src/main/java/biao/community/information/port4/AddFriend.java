package biao.community.information.port4;

public class AddFriend {

    String u_id;
    String uf_id;
    String uf_source;

    public String getUf_source() {
        return uf_source;
    }

    public void setUf_source(String uf_source) {
        this.uf_source = uf_source;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getUf_id() {
        return uf_id;
    }

    public void setUf_id(String uf_id) {
        this.uf_id = uf_id;
    }
}
