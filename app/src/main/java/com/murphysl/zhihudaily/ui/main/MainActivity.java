package com.murphysl.zhihudaily.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.mvpframe.base.BaseActivity;

import butterknife.BindView;


public class MainActivity extends BaseActivity{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.activity_main)
    DrawerLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        activityMain = (DrawerLayout) findViewById(R.id.activity_main);

        //setToolBar(toolbar , R.string.toolbar_title_main);
        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityMain.isDrawerOpen(Gravity.LEFT)){
                    activityMain.closeDrawers();
                }else{
                    activityMain.openDrawer(Gravity.LEFT);
                }
            }
        });
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, mainFragment);
        fragmentTransaction.commit();
    }
}
