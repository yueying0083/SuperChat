package cn.yueying0083.superchat.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.yueying0083.libaray.R;

/**
 * Created by yueying_0083@qq.com on 2017/4/5.
 */

public abstract class LinkMessage extends BaseMessage {
    private String message;
    private List<Link> list = new ArrayList<>();

    public LinkMessage(String message, long time, Link... links) {
        super(time);
        this.message = message;
        Collections.addAll(list, links);
    }

    public LinkMessage(String message, long time, String avatar, Link... links) {
        super(time, avatar);
        this.message = message;
        Collections.addAll(list, links);
    }

    @Override
    public View getChatContentView(@NonNull Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.link_message, null, false);
        TextView msgView = (TextView) v.findViewById(R.id.tv_msg);
        LinearLayoutCompat ll = (LinearLayoutCompat) v.findViewById(R.id.ll_links);
        if (!TextUtils.isEmpty(message)) {
            msgView.setText(message);
            msgView.setVisibility(View.VISIBLE);
        } else {
            msgView.setVisibility(View.GONE);
        }
        if (list != null && !list.isEmpty()) {
            ll.removeAllViews();
            for (Link link : list) {
                TextView template = new TextView(context);
                template.setTextColor(Color.BLUE);
                template.setBackgroundResource(R.drawable.bg_link_selector);
                template.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                template.getPaint().setAntiAlias(true);
                template.setText(link.getShow());
                template.setClickable(true);
                template.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                ll.addView(template);
            }
            ll.setVisibility(View.VISIBLE);
        } else {
            ll.removeAllViews();
            ll.setVisibility(View.GONE);
        }
        return v;
    }

    public static class Link {
        public Link(){

        }

        public Link(String show){
            this.show = show;
        }

        private String tag;
        private String show;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getShow() {
            return show;
        }

        public void setShow(String show) {
            this.show = show;
        }
    }

    public static class LeftLinkMessage extends LinkMessage{

        public LeftLinkMessage(String message, long time, Link... links) {
            super(message, time, links);
        }

        public LeftLinkMessage(String message, long time, String avatar, Link... links) {
            super(message, time, avatar, links);
        }

        @Override
        public ChatType getChatType() {
            return ChatType.LEFT;
        }
    }

    public static class RightLinkMessage extends LinkMessage{

        public RightLinkMessage(String message, long time, Link... links) {
            super(message, time, links);
        }

        public RightLinkMessage(String message, long time, String avatar, Link... links) {
            super(message, time, avatar, links);
        }

        @Override
        public ChatType getChatType() {
            return ChatType.RIGHT;
        }
    }
}
