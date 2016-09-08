package com.wyf.daike.Util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyf.daike.R;

import rx.BackpressureOverflow;

/**
 * Created by wyf on 2016/8/15.
 */
public class LoadImgUtil {


    public  static  void loadImg(Context context, ImageView imageView,String url)
    {

        Glide.with(context).load(url).error(R.drawable.ly).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

    }
}
