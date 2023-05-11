package biao.community.information.port5_6__9;

public class Result {
    String error = "";
    String state = "success";

    public String getError() {
        return error;
    }

    public String getState() {
        return state;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setState(String state) {
        this.state = state;
    }
}
