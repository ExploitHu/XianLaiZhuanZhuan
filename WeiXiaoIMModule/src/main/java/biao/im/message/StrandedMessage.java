package biao.im.message;


//滞留消息
public class StrandedMessage {
    //发送人
    String u_id1;
    //接收人
    String u_id2;
    //消息内容
    String dia_message;
    //发送时间
    String dia_time;
    //辨识码
    String md5;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getU_id1() {
        return u_id1;
    }

    public void setU_id1(String u_id1) {
        this.u_id1 = u_id1;
    }

    public String getU_id2() {
        return u_id2;
    }

    public void setU_id2(String u_id2) {
        this.u_id2 = u_id2;
    }

    public String getDia_message() {
        return dia_message;
    }

    public void setDia_message(String dia_message) {
        this.dia_message = dia_message;
    }

    public String getDia_time() {
        return dia_time;
    }

    public void setDia_time(String dia_time) {
        this.dia_time = dia_time;
    }

}
