package com.murphysl.zhihudaily.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.murphysl.zhihudaily.config.Constants;

/**
 * SharedPreferencesUtils
 *
 * @author: MurphySL
 * @time: 2017/2/10 15:46
 */


public class SharedPreferencesUtils {

    private Context context;

    public SharedPreferencesUtils(Context context) {
        this.context = context;
    }

    public void saveSuffix(String suffix){
        SharedPreferences sp = context.getSharedPreferences(Constants.SHARED_NAME,Context.MODE_PRIVATE);
        sp.edit().putString(Constants.SKIN_SUFFIX_KEY , suffix).apply();
    }

    public String getSuffix(){
        SharedPreferences sp = context.getSharedPreferences(Constants.SHARED_NAME,Context.MODE_PRIVATE);
        return sp.getString(Constants.SKIN_SUFFIX_KEY , "");
    }

    public void clear(){
        SharedPreferences sp = context.getSharedPreferences(Constants.SHARED_NAME,Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
}
