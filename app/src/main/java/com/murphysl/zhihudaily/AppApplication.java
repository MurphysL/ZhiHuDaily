package com.murphysl.zhihudaily;

import android.app.Application;

import com.murphysl.zhihudaily.ui.skin.SkinManager;
import com.murphysl.zhihudaily.util.SharedPreferencesUtils;
import com.orhanobut.logger.Logger;

/**
 * AppApplication
 *
 * @author: MurphySL
 * @time: 2017/2/8 20:47
 */


public class AppApplication extends Application {
    private static final String TAG = "MurphySL";

    @Override
    public void onCreate() {
        super.onCreate();

        SkinManager.getInstance().init(this);

        Logger.init(TAG).methodCount(3);


    }
}
