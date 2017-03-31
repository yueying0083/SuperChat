package cn.yueying0083.superchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.yueying0083.superchat.model.BaseChatModel;
import cn.yueying0083.superchat.model.LeftTextMessage;
import cn.yueying0083.superchat.model.RightTextMessage;
import cn.yueying0083.superchat.utils.ImageDisplay;
import cn.yueying0083.superchat.view.ChatListView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.clv)
    ChatListView chatListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        List<BaseChatModel> list = new ArrayList<>();
        list.add(new LeftTextMessage("hello!", 1490150482788L, "assets://left.png"));
        list.add(new RightTextMessage("hi, I'm jack", 1490250582788L, "assets://right.png"));
        list.add(new LeftTextMessage("nice 2CU!", 1490250782788L, "assets://left.png"));
        list.add(new RightTextMessage("^ v ^!", 1490350882788L, "assets://right.png"));
        list.add(new RightTextMessage("I'd like to send you some large message, just like this: " +
                "message along with the name of the " +
                "caller and can view the list of" +
                " unplayed and played messages.", 1490450882788L, "assets://right.png"));
        list.add(new LeftTextMessage("I have received it, it's amazing!", 1490550782788L, "assets://left.png"));
        list.add(new LeftTextMessage("I'd like to send some photo 2U!", 1490650782788L, "assets://left.png"));
        chatListView.setImageLoader(new ImageDisplay());
        chatListView.updateList(list);
    }
}
