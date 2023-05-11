package biao.community.information.port4_3;

public class OUInformation {

    String u_id;
    String u_head;
    String u_sex;
    String u_nickname;
    String u_identity;
    String u_city;
    String u_like;
    boolean isFriend;

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public boolean isisFriend() {
        return isFriend;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_head() {
        return u_head;
    }

    public void setU_head(String u_head) {
        this.u_head = u_head;
    }

    public String getU_sex() {
        return u_sex;
    }

    public void setU_sex(String u_sex) {
        this.u_sex = u_sex;
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public String getU_identity() {
        return u_identity;
    }

    public void setU_identity(String u_identity) {
        this.u_identity = u_identity;
    }

    public String getU_city() {
        return u_city;
    }

    public void setU_city(String u_city) {
        this.u_city = u_city;
    }

    public String getU_like() {
        return u_like;
    }

    public void setU_like(String u_like) {
        this.u_like = u_like;
    }
}
