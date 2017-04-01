package cn.yueying0083.superchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.yueying0083.superchat.model.BaseMessage;
import cn.yueying0083.superchat.model.TextMessage.*;
import cn.yueying0083.superchat.model.ImageMessage.*;
import cn.yueying0083.superchat.utils.ImageDisplay;
import cn.yueying0083.superchat.utils.MyTimelineFormatter;
import cn.yueying0083.superchat.view.ChatListView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.clv)
    ChatListView chatListView;

    private ImageDisplay mImageDisplay;
    private boolean prevLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Picasso.with(this).load("file:///android_asset/left.png").fetch();
        Picasso.with(this).load("file:///android_asset/right.png").fetch();
        Picasso.with(this).load("file:///android_asset/screen_shot_1.png").fetch();
        Picasso.with(this).load("file:///android_asset/tree.JPG").fetch();

        mImageDisplay = new ImageDisplay();

        List<BaseMessage> messageList = new ArrayList<>();
        messageList.add(new LeftTextMessage("hello!", 1490150482788L, "file:///android_asset/left.png"));
        messageList.add(new RightTextMessage("hi, I'm jack", 1490250582788L, "file:///android_asset/right.png"));
        messageList.add(new LeftTextMessage("nice 2CU!", 1491025381246L, "file:///android_asset/left.png"));
        messageList.add(new RightTextMessage("^ v ^!", 1491025383246L, "file:///android_asset/right.png"));
        messageList.add(new RightTextMessage("I'd like to send you some large message, just like this: " +
                "message along with the name of the " +
                "caller and can view the list of" +
                " unplayed and played messages.", 1491025389246L, "file:///android_asset/right.png"));
        messageList.add(new LeftTextMessage("I have received it, it's amazing!", 1491025389746L, "file:///android_asset/left.png"));
        messageList.add(new LeftTextMessage("I'd like to send some photo 2U!", 1491025409746L, "file:///android_asset/left.png"));
        messageList.add(new LeftImageMessage("file:///android_asset/screen_shot_1.png", 1491025411746L, mImageDisplay, "file:///android_asset/left.png"));
        messageList.add(new RightTextMessage("^ v ^!", 1491025419746L, "file:///android_asset/right.png"));
        messageList.add(new RightImageMessage("file:///android_asset/tree.JPG", 1491025541430L, mImageDisplay, "file:///android_asset/left.png"));

        Logger.d(new Gson().toJson(messageList));

        chatListView.setImageLoader(mImageDisplay);
        chatListView.setTimelineFormatter(new MyTimelineFormatter());
        chatListView.setPrevMessageLoader(mPrevMessageLoader);
        chatListView.updateList(messageList);
    }

    private ChatListView.PrevMessageLoader mPrevMessageLoader = new ChatListView.PrevMessageLoader() {
        @Override
        public List<BaseMessage> getPrevMessage() {
            if (!prevLoaded) {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prevLoaded = true;
                List<BaseMessage> messageList = new ArrayList<>();
                messageList.add(new LeftTextMessage("hello!", 1490110111181L, "file:///android_asset/left.png"));
                messageList.add(new RightTextMessage("hi, I'm jack", 1490110111188L, "file:///android_asset/right.png"));
                messageList.add(new LeftTextMessage("nice 2CU!", 1490110111788L, "file:///android_asset/left.png"));
                messageList.add(new RightTextMessage("^ v ^!", 1490110112788L, "file:///android_asset/right.png"));
                messageList.add(new RightTextMessage("I'd like to send you some large message, just like this: " +
                        "message along with the name of the " +
                        "caller and can view the list of" +
                        " unplayed and played messages.", 1490110212788L, "file:///android_asset/right.png"));
                messageList.add(new LeftTextMessage("I have received it, it's amazing!", 1490111212788L, "file:///android_asset/left.png"));
                messageList.add(new LeftTextMessage("I'd like to send some photo 2U!", 1490150212788L, "file:///android_asset/left.png"));
                messageList.add(new LeftImageMessage("file:///android_asset/screen_shot_1.png", 1490150412788L, mImageDisplay, "file:///android_asset/left.png"));
                messageList.add(new RightTextMessage("^ v ^!", 1490150422788L, "file:///android_asset/right.png"));
                messageList.add(new RightImageMessage("file:///android_asset/tree.JPG", 1490150432788L, mImageDisplay, "file:///android_asset/left.png"));
                return messageList;
            }
            return null;
        }
    };
}
