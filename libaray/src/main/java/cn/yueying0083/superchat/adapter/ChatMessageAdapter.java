package cn.yueying0083.superchat.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.List;

import cn.yueying0083.superchat.model.BaseChatModel;

/**
 * Created by luoj@huoli.com on 2017/3/30.
 */

public class ChatMessageAdapter extends ArrayAdapter<BaseChatModel> {


    public ChatMessageAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<BaseChatModel> objects) {
        super(context, resource, objects);
    }
}
