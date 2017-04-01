package cn.yueying0083.superchat.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by yueying_0083@qq.com on 2017/3/31.
 */

public class ImageDisplay implements ImageLoader {

    @Override
    public void loadImage(ImageView iv, String uri) {
        Picasso.with(iv.getContext()).load(uri).into(iv);
    }
}
