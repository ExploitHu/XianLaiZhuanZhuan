package biao.im.message;

public class ResultMessage {
    private boolean isSystem;
    private String fromName;
    private String time;
    private Object message;
    private String md5;

    ResultMessage() {
    }

    public ResultMessage(boolean isSystem, String fromName, String time, Object message, String md5) {
        this.isSystem = isSystem;
        this.fromName = fromName;
        this.message = message;
        this.time = time;
        this.md5 = md5;
    }

    public void setIsSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }


    public String getTime() {
        return this.time;
    }

    public String getFromName() {
        return this.fromName;
    }

    public boolean getIsSystem() {
        return this.isSystem;
    }

    public Object getMessage() {
        return this.message;
    }

    public String getMd5() {
        return this.md5;
    }

}
