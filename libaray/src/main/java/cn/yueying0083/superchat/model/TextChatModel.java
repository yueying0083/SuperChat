package cn.yueying0083.superchat.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by luoj@huoli.com on 2017/3/31.
 */

public abstract class TextChatModel extends BaseChatModel {

    private String textMessage;

    public TextChatModel(String textMessage, long time) {
        this.textMessage = textMessage;
        this.chatDateTime = time;
    }

    @Override
    public View getChatContentView(@NonNull Context context) {
        TextView tv = new TextView(context);
        tv.setText(textMessage);
        return tv;
    }

}
