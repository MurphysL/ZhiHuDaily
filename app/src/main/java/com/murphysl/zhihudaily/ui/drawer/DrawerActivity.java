package com.murphysl.zhihudaily.ui.drawer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.mvpframe.base.BaseActivity;
import com.murphysl.zhihudaily.ui.splash.SplashFragment;

/**
 * DrawerActivity
 *
 * @author: MurphySL
 * @time: 2017/1/28 18:14
 */


public class DrawerActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        SplashFragment splashFragment = new SplashFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, splashFragment);
        fragmentTransaction.commit();
    }
}
