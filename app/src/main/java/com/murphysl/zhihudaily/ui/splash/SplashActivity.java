package com.murphysl.zhihudaily.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.FrameLayout;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.mvpframe.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * SplashActivity
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:41
 */


public class SplashActivity extends BaseActivity {


    @BindView(R.id.content)
    FrameLayout splashContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

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
