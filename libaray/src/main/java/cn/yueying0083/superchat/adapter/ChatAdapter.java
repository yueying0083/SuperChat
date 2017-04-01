package cn.yueying0083.superchat.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.yueying0083.libaray.R;
import cn.yueying0083.superchat.model.BaseMessage;
import cn.yueying0083.superchat.utils.ImageLoader;

/**
 * Created by luoj@huoli.com on 2017/3/30.
 */

public class ChatAdapter extends BaseAdapter {

    private List<BaseMessage> mChatList;
    private ImageLoader mImageLoader;
    private TimelineFormatter mTimelineFormatter;

    public ChatAdapter(List<BaseMessage> chatList) {
        this.mChatList = chatList;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
        if (mImageLoader != null) {
            notifyDataSetChanged();
        }
    }

    public void setTimelineFormatter(TimelineFormatter timelineFormatter) {
        mTimelineFormatter = timelineFormatter;
        if (mTimelineFormatter != null) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mChatList == null ? 0 : mChatList.size();
    }

    @Override
    public BaseMessage getItem(int position) {
        return mChatList == null ? null : mChatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        View v = null;
        if (convertView != null) {
            Object obj = convertView.getTag();
            if (obj != null && obj instanceof ViewHolder) {
                v = convertView;
                vh = (ViewHolder) obj;
            }
        }

        if (v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
            vh = new ViewHolder();
            vh.timeline = (TextView) v.findViewById(R.id.tv_timeline);
            vh.left = v.findViewById(R.id.ll_left);
            vh.avatarLeft = (ImageView) v.findViewById(R.id.iv_left_avatar);
            vh.contentLeft = (FrameLayout) v.findViewById(R.id.fl_left_content);
            vh.right = v.findViewById(R.id.ll_right);
            vh.avatarRight = (ImageView) v.findViewById(R.id.iv_right_avatar);
            vh.contentRight = (FrameLayout) v.findViewById(R.id.fl_right_content);
            v.setTag(vh);
        }

        vh.contentLeft.removeAllViews();
        vh.contentRight.removeAllViews();

        BaseMessage model = getItem(position);

        long prevTime = position > 0 ? getItem(position - 1).getChatDateTime() : -1L;

        if (mTimelineFormatter != null) {
            String timeline = mTimelineFormatter.format(model.getChatDateTime(), prevTime);
            if (!TextUtils.isEmpty(timeline)) {
                vh.timeline.setText(timeline);
                vh.timeline.setVisibility(View.VISIBLE);
            } else {
                vh.timeline.setVisibility(View.GONE);
            }
        } else {
            vh.timeline.setVisibility(View.GONE);
        }

        FrameLayout content = null;
        ImageView avatar = null;
        switch (model.getChatType()) {
            case LEFT:
                vh.right.setVisibility(View.GONE);
                vh.left.setVisibility(View.VISIBLE);
                content = vh.contentLeft;
                avatar = vh.avatarLeft;
                break;
            case RIGHT:
                vh.right.setVisibility(View.VISIBLE);
                vh.left.setVisibility(View.GONE);
                content = vh.contentRight;
                avatar = vh.avatarRight;
                break;
        }

        content.setBackgroundResource(model.needBackground() ? R.drawable.bg_content_normal : android.R.color.transparent);
        content.addView(model.getChatContentView(parent.getContext()));
        if (model.enableAvatar()) {
            avatar.setVisibility(View.VISIBLE);
            if (mImageLoader != null) {
                mImageLoader.loadImage(avatar, model.getAvatarUri());
            }
        } else {
            avatar.setVisibility(View.GONE);
        }
        return v;
    }

    public void setChatList(List<BaseMessage> list) {
        this.mChatList = list;
        notifyDataSetChanged();
    }

    public void add(BaseMessage model) {
        mChatList.add(model);
        notifyDataSetChanged();
    }

    public void addAll(int position, List<BaseMessage> list) {
        mChatList.addAll(position, list);
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView timeline;
        View left;
        ImageView avatarLeft;
        FrameLayout contentLeft;
        View right;
        ImageView avatarRight;
        FrameLayout contentRight;
    }

    public interface TimelineFormatter {
        /**
         * @param current: current time mills
         * @param prev:    prev time mills
         * @return timeline: show timeline
         * null: Do not show timeline
         */
        public String format(long current, long prev);
    }
}
