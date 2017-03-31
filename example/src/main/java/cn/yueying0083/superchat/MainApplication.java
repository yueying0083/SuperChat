package cn.yueying0083.superchat;

import android.app.Application;
import android.content.Context;

/**
 * Created by luoj@huoli.com on 2017/3/31.
 */

public class MainApplication extends Application {
    private static Context mApplicationContext = null;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationContext = getApplicationContext();

    }

    public static Context getContext() {
        return mApplicationContext;
    }
}
