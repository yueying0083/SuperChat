package cn.yueying0083.superchat.model;

/**
 * Created by luoj@huoli.com on 2017/3/30.
 */

public class RightTextMessage extends TextChatModel {
    public RightTextMessage(String textMessage, long time) {
        super(textMessage, time);
    }

    public RightTextMessage(String textMessage, long time, String avatarUri) {
        super(textMessage, time);
        this.avatarUri = avatarUri;
    }

    @Override
    public ChatType getChatType() {
        return ChatType.RIGHT;
    }


}
