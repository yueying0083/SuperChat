package cn.yueying0083.superchat.utils;

import android.content.Context;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import cn.yueying0083.superchat.MainApplication;

/**
 * Created by luoj@huoli.com on 2017/3/31.
 */

public class ImageDisplay implements ImageLoader {

    static {
        Context context = MainApplication.getContext();
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO);
        ImageLoaderConfiguration config = builder.build();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config);
    }


    @Override
    public void loadImage(ImageView iv, String uri) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(uri, iv);
    }
}
