package com.murphysl.zhihudaily.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Window;

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


//    @BindView(R.id.frag_splash)
//    Fragment fragSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_act);
        ButterKnife.bind(this);

    }

    @Override
    protected void initFragment() {
        super.initFragment();
//        SplashFragment splashFragment =
//                (SplashFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//        if(fragSplash == null )
//        {
//            mContentFragment = new ContentFragment();
//            fm.beginTransaction().add(R.id.id_fragment_container,mContentFragment).commit();
//        }
    }
}
