package com.wyf.daike.Util;

import android.content.Context;
import android.content.SharedPreferences;

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
        SharedPreferences getData = context.getSharedPreferences("data",0);
        return  getData.getString(key,"");
    }


}
