package cn.yueying0083.superchat.model;

/**
 * Created by luoj@huoli.com on 2017/3/30.
 */

public abstract class BaseChatModel {

    public enum ChatType {
        LEFT, RIGHT
    }

    int id;
    long chatDateTime;
    String picUri;

    public abstract ChatType getChatType();

    public abstract ChatContent getChatContent();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getChatDateTime() {
        return chatDateTime;
    }

    public void setChatDateTime(long chatDateTime) {
        this.chatDateTime = chatDateTime;
    }

    public String getPicUri() {
        return picUri;
    }

    public void setPicUri(String picUri) {
        this.picUri = picUri;
    }

}
