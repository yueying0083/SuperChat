package cn.yueying0083.superchat.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.yueying0083.libaray.R;
import cn.yueying0083.superchat.adapter.ChatAdapter;
import cn.yueying0083.superchat.model.BaseChatModel;
import cn.yueying0083.superchat.utils.ImageLoader;

/**
 * Created by luoj@huoli.com on 2017/3/30.
 */

public class ChatListView extends ListView {
    private static final String TAG = "ChatListView";

    private static final int ONE_DAY_SEC = Long.valueOf(TimeUnit.DAYS.toSeconds(1)).intValue();

    private ChatAdapter mChatAdapter;

    private boolean mEnableLoadPrev;// enable load prev chat data
    private View mHeaderView;// head view show loading prev data progress
    private OnDataLoadListener mOnDataLoadListener;// load prev data listener
    private boolean mIsLoadingPrevData = false;
    private int mCountEachTime = -1;

    public ChatListView(Context context) {
        super(context);
        initialize(null, 0);
    }

    public ChatListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs, 0);
    }

    public ChatListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs, defStyleAttr);
    }

    private void initialize(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ChatListView, defStyleAttr, 0);

        int timelineInterval = a.getInteger(R.styleable.ChatListView_clv_timeline_interval, ONE_DAY_SEC);
        mEnableLoadPrev = a.getBoolean(R.styleable.ChatListView_clv_enable_load_prev, false);
        mCountEachTime = a.getInteger(R.styleable.ChatListView_clv_count_each_time, Integer.MAX_VALUE);

        a.recycle();

        if (mEnableLoadPrev) {
            mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.loading, this, false);
            addHeaderView(mHeaderView);
            setOnScrollListener(mOnScrollListener);
        }
        setTranscriptMode(TRANSCRIPT_MODE_NORMAL);

        mChatAdapter = new ChatAdapter(null);
        mChatAdapter.updateTimelineInterval(timelineInterval);
        setAdapter(mChatAdapter);
    }

    private OnScrollListener mOnScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (mEnableLoadPrev && mOnDataLoadListener != null) {
                switch (scrollState) {
                    case OnScrollListener.SCROLL_STATE_IDLE:
                        if (getFirstVisiblePosition() == 0) {
                            loadPrevData();
                        }
                        break;
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        }
    };

    private synchronized void loadPrevData() {
        new Thread() {

            public void run() {
                mIsLoadingPrevData = true;
                final List<BaseChatModel> list = mOnDataLoadListener.getPrev();

                if (list == null || list.isEmpty() || (mCountEachTime > 0 && list.size() < mCountEachTime)) {
                    mIsLoadingPrevData = false;
                    setOnScrollListener(null);
                    removeHeaderView(mHeaderView);
                    return;
                }

                Collections.sort(list, new Comparator<BaseChatModel>() {
                    @Override
                    public int compare(BaseChatModel m1, BaseChatModel m2) {
                        return Long.valueOf(m1.getChatDateTime() - m2.getChatDateTime()).intValue();
                    }
                });

                post(new Runnable() {
                    @Override
                    public void run() {
                        mChatAdapter.addAll(0, list);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            setSelectionFromTop(list.size() + 1, mHeaderView.getHeight());
                        }
                    }
                });
            }

        }.start();
    }

    /**
     * Update data in chat list
     *
     * @param list chat list
     */
    public void updateList(List<BaseChatModel> list) {
        if (list == null) {
            return;
        }

        int count = list.size();
        if (mEnableLoadPrev) {
            if (mCountEachTime == -1) {
                Log.d(TAG, "you may need to set CountEachTime to ensure head view displayable!");
            } else if (count < mCountEachTime) {
                removeHeaderView(mHeaderView);
            }
        }

        Collections.sort(list, new Comparator<BaseChatModel>() {
            @Override
            public int compare(BaseChatModel m1, BaseChatModel m2) {
                return Long.valueOf(m1.getChatDateTime() - m2.getChatDateTime()).intValue();
            }
        });
        mChatAdapter.setChatList(list);
    }

    /**
     * Set ImageLoader to load image for avatar
     *
     * @param imageLoader imageLoader
     */
    public void setImageLoader(ImageLoader imageLoader) {
        mChatAdapter.setImageLoader(imageLoader);
    }

    /**
     * Add message
     *
     * @param chat chat add at bottom
     */
    public void newChat(BaseChatModel chat) {

    }

    public void setOnDataLoadListener(OnDataLoadListener onDataLoadListener) {
        mOnDataLoadListener = onDataLoadListener;
    }

    /**
     *
     */
    public interface OnDataLoadListener {
        /**
         * We would like to load old data into chat list when scroll to top.
         */
        public List<BaseChatModel> getPrev();
    }

}
