package cn.yueying0083.superchat.model;

/**
 * Created by luoj@huoli.com on 2017/3/30.
 */

public class LeftTextMessage extends BaseChatModel {

    @Override
    public BaseChatModel.ChatType getChatType() {
        return ChatType.LEFT;
    }

    @Override
    public ChatContent getChatContent() {
        return null;
    }

}
