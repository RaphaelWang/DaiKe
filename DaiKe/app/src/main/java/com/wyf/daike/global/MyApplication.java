package com.wyf.daike.global;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2016/7/30.
 */
public class MyApplication extends Application {
    public  static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "4772582cf6fe2d0e7aa5b4b3ec251119");
        context = getApplicationContext();
    }
    public static Context getContext()
    {
        return context;
    }
}
