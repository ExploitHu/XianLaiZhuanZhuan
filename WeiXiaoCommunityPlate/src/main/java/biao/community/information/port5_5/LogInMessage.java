package biao.community.information.port5_5;

public class LogInMessage {
    String u_email;
    String passworldMD5;
    String u_id;

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getPassworldMD5() {
        return passworldMD5;
    }

    public void setPassworldMD5(String passworldMD5) {
        this.passworldMD5 = passworldMD5;
    }
}
