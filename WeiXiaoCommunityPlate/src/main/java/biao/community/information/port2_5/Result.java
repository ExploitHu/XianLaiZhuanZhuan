package biao.community.information.port2_5;

public class Result {

    private String bt_id;
    private String btc_id;
    private String u_id0;
    private String u_id;
    private String btc_com;
    private String btc_time;

    int code = 200;
    String errorMessage = "success";

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setBt_id(String bt_id) {
        this.bt_id = bt_id;
    }

    public void setBtc_id(String btc_id) {
        this.btc_id = btc_id;
    }

    public void setU_id0(String u_id0) {
        this.u_id0 = u_id0;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public void setBtc_com(String btc_com) {
        this.btc_com = btc_com;
    }

    public void setBtc_time(String btc_time) {
        this.btc_time = btc_time;
    }

    public String getBt_id() {
        return bt_id;
    }

    public String getBtc_id() {
        return btc_id;
    }

    public String getU_id0() {
        return u_id0;
    }

    public String getU_id() {
        return u_id;
    }

    public String getBtc_com() {
        return btc_com;
    }

    public String getBtc_time() {
        return btc_time;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
