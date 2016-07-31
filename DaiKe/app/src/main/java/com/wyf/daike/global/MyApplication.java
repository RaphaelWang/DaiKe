package com.wyf.daike.global;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/7/30.
 */
public class MyApplication extends Application {
    public  static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext()
    {
        return context;
    }
}
