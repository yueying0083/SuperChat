package cn.yueying0083.superchat.model;

/**
 * Created by luoj@huoli.com on 2017/3/30.
 */

public class RightTextMessage extends TextChatModel {
    public RightTextMessage(String textMessage) {
        super(textMessage);
    }

    @Override
    public ChatType getChatType() {
        return ChatType.RIGHT;
    }


}
