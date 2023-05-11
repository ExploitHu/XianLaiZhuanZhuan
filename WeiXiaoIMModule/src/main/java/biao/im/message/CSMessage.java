package biao.im.message;

import biao.im.tool.Tool;
import com.alibaba.fastjson.JSONObject;

public class CSMessage {

    boolean isSystem;
    String sendId;
    String reId;
    String type = "String";
    String message;
    String sendTime;
    String md5;
    JSONObject other;

    public JSONObject getOther() {
        return other;
    }

    public void setOther(JSONObject other) {
        this.other = other;
    }

    public CSMessage(){}

    //心跳回复构造函数
    public CSMessage(String time,String message){
        if(message.equals("心跳回复")){
            this.sendTime = time;
            this.message = message;
            this.isSystem = true;
        }
    }

    //return构造函数
    public CSMessage(boolean isSystem,String sendId,String md5,String sendTime){
        this.isSystem = isSystem;
        this.sendTime =sendTime;
        this.sendId =sendId;
        this.md5 = md5;
    }

    public JSONObject toJsonObject(){
        return Tool.classToJson(this);
    }

    public boolean isIsSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getReId() {
        return reId;
    }

    public void setReId(String reId) {
        this.reId = reId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
