package cn.yueying0083.superchat.model;

/**
 * Created by luoj@huoli.com on 2017/3/30.
 */

public class RightTextMessage extends BaseChatModel {
    @Override
    public ChatType getChatType() {
        return ChatType.RIGHT;
    }

    @Override
    public ChatContent getChatContent() {
        return null;
    }

}
