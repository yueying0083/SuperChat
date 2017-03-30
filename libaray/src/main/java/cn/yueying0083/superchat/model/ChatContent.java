package cn.yueying0083.superchat.model;

import android.view.View;

/**
 * Created by luoj@huoli.com on 2017/3/30.
 */

public interface ChatContent {

    View getView();

    boolean needBackground();
}
