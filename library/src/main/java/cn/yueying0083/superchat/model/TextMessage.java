package cn.yueying0083.superchat.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yueying_0083@qq.com on 2017/3/31.
 */

public abstract class TextMessage extends BaseMessage {

    private String textMessage;

    public TextMessage(String textMessage, long time) {
        super(time);
        this.textMessage = textMessage;
    }

    public TextMessage(String textMessage, long time, String avatar) {
        super(time, avatar);
        this.textMessage = textMessage;
    }

    @Override
    public View getChatContentView(@NonNull Context context) {
        TextView tv = new TextView(context);
        tv.setText(textMessage);
        return tv;
    }

    public static class LeftTextMessage extends TextMessage {

        public LeftTextMessage(String textMessage, long time) {
            super(textMessage, time);
        }

        public LeftTextMessage(String textMessage, long time, String avatarUri) {
            super(textMessage, time, avatarUri);
        }

        @Override
        public BaseMessage.ChatType getChatType() {
            return ChatType.LEFT;
        }

    }

    public static class RightTextMessage extends TextMessage {
        public RightTextMessage(String textMessage, long time) {
            super(textMessage, time);
        }

        public RightTextMessage(String textMessage, long time, String avatarUri) {
            super(textMessage, time, avatarUri);
        }

        @Override
        public ChatType getChatType() {
            return ChatType.RIGHT;
        }


    }


}
