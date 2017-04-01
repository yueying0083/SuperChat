package cn.yueying0083.superchat.utils;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import cn.yueying0083.superchat.adapter.ChatAdapter.TimelineFormatter;

/**
 * Created by yueying_0083@qq.com on 2017/4/1.
 */

public class MyTimelineFormatter implements TimelineFormatter {
    @Override
    public String format(long current, long prev) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(current);

        if (dayEquals(now, c)) {
            if (TimeUnit.MILLISECONDS.toHours(current - prev) > 1) {
                return new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Date(current));
            } else {
                return null;
            }
        } else {
            if (prev != -1) {
                Calendar p = Calendar.getInstance();
                p.setTimeInMillis(prev);
                if (dayEquals(p, c)) {
                    return null;
                }
            }
            return new SimpleDateFormat("MM-dd", Locale.CHINA).format(new Date(current));
        }
    }

    private boolean dayEquals(@NonNull Calendar c1, @NonNull Calendar c2) {
        return c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }
}
