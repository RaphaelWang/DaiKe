package com.wyf.daike.Util;

import android.content.Context;
import android.content.SharedPreferences;

import rx.internal.util.atomic.SpscAtomicArrayQueue;

/**
 * Created by wyf on 2016/8/15.
 */
public class SharedPresferencesUtil {





    public static void saveData(Context context,String key, String value)
    {
        SharedPreferences.Editor savaData =  context.getSharedPreferences("data",context.MODE_PRIVATE).edit();
        savaData.putString(key,value);
        savaData.commit();
    }

    public static  String  getData(Context context ,String key)

    {
        SharedPreferences getData = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        return  getData.getString(key,"");
    }

    /**
     * 保存夜间模式
     */
    public static  void  setNight(Context context,String key,boolean isNight)
    {
        SharedPreferences.Editor saveNight = context.getSharedPreferences("data",Context.MODE_PRIVATE).edit();
        saveNight.putBoolean(key,isNight);
        saveNight.commit();
    }
    public static boolean getNight(Context context,String key)
    {
       SharedPreferences isNight = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        return  isNight.getBoolean(key,false);
    }


}
