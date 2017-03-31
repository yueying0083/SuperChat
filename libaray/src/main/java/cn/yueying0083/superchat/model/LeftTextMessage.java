package cn.yueying0083.superchat.model;

import android.view.View;

/**
 * Created by luoj@huoli.com on 2017/3/30.
 */

public class LeftTextMessage extends TextChatModel {

    public LeftTextMessage(String textMessage, long time) {
        super(textMessage, time);
    }

    public LeftTextMessage(String textMessage, long time, String avatarUri) {
        super(textMessage, time);
        this.avatarUri = avatarUri;
    }

    @Override
    public BaseChatModel.ChatType getChatType() {
        return ChatType.LEFT;
    }

}
