package cn.yueying0083.superchat.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by yueying_0083@qq.com on 2017/3/30.
 */

public abstract class BaseMessage {

    public enum ChatType {
        LEFT, RIGHT
    }

    int id;
    long chatDateTime;
    String avatarUri;

    /**
     * Type of chat, left or right
     *
     * @return
     */
    public abstract ChatType getChatType();

    /**
     * Custom view to use in chat content
     *
     * @return
     */
    public abstract View getChatContentView(@NonNull Context context);

    /**
     * @return Auto add background to chat content
     */
    public boolean needBackground() {
        return true;
    }

    /**
     * Enable head picture
     *
     * @return
     */
    public boolean enableAvatar() {
        return true;
    }

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

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }
}
