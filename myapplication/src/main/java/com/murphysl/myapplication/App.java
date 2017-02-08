package com.murphysl.myapplication;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * App
 *
 * @author: MurphySL
 * @time: 2017/2/8 21:07
 */


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("MurphySL");
    }
}
