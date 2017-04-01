package cn.yueying0083.superchat.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import cn.yueying0083.superchat.utils.ImageLoader;

/**
 * Created by yueying_0083@qq.com on 2017/3/31.
 */

public abstract class ImageMessage extends BaseMessage {

    private String mImageUri;
    private ImageLoader mImageLoader;

    public ImageMessage(String imageUri, long time, ImageLoader imageLoader) {
        this.mImageUri = imageUri;
        this.chatDateTime = time;
        this.mImageLoader = imageLoader;
    }

    public ImageMessage(String imageUri, long time, ImageLoader imageLoader, String avatarUri) {
        this.mImageUri = imageUri;
        this.chatDateTime = time;
        this.mImageLoader = imageLoader;
        this.avatarUri = avatarUri;
    }

    @Override
    public View getChatContentView(@NonNull Context context) {
        ImageView iv = new android.support.v7.widget.AppCompatImageView(context){
            private static final int MAX_WIDTH = 360;

            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                Drawable d = getDrawable();
                if(d != null){
                    int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
                    viewWidth = viewWidth > MAX_WIDTH ? MAX_WIDTH : viewWidth;
                    int viewHeight = (int) (d.getIntrinsicHeight() / (float) d.getIntrinsicWidth() * viewWidth);
                    setMeasuredDimension(viewWidth, viewHeight);
                } else {
                    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                }
            }
        };
        mImageLoader.loadImage(iv, mImageUri);
        return iv;
    }

    public static class LeftImageMessage extends ImageMessage {

        public LeftImageMessage(String imageUri, long time, ImageLoader imageLoader) {
            super(imageUri, time, imageLoader);
        }

        public LeftImageMessage(String imageUri, long time, ImageLoader imageLoader, String avatarUri) {
            super(imageUri, time, imageLoader, avatarUri);
        }

        @Override
        public ChatType getChatType() {
            return ChatType.LEFT;
        }
    }

    public static class RightImageMessage extends ImageMessage {

        public RightImageMessage(String imageUri, long time, ImageLoader imageLoader) {
            super(imageUri, time, imageLoader);
        }

        public RightImageMessage(String imageUri, long time, ImageLoader imageLoader, String avatarUri) {
            super(imageUri, time, imageLoader, avatarUri);
        }

        @Override
        public ChatType getChatType() {
            return ChatType.RIGHT;
        }
    }


}
