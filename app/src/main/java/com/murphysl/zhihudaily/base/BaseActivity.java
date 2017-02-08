package com.murphysl.zhihudaily.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.murphysl.zhihudaily.Constants;
import com.murphysl.zhihudaily.R;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * BaseActivity
 *
 * @author: MurphySL
 * @time: 2017/1/28 18:53
 */


public abstract class BaseActivity extends AppCompatActivity {

    private long exitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initView();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(getIntent() != null){
            handleIntent(getIntent());
        }
        if(getSupportFragmentManager() != null){
            BaseFragment firstFragment = getFirstFragment();
            if(firstFragment != null)
                addFragment(firstFragment);
        }
        initData();
    }

    protected void initData(){ }

    protected abstract void initView();

    protected void handleIntent (Intent intent){ }

    protected View getRootView(){
        return this.getWindow().getDecorView();
    }

    protected int getContentViewId(){ return R.layout.activity_main;}

    protected int getFragmentViewId(){ return R.id.content;}

    protected BaseFragment getFirstFragment(){ return null;}

    protected void addFragment(BaseFragment fragment){
        if(fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentViewId() , fragment , fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();//允许异常丢失时可以正常提交
        }
    }

    protected void popFragment(){
        if(getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStack();
        }else{
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(KeyEvent.KEYCODE_BACK == keyCode){
            if(getSupportFragmentManager().getBackStackEntryCount() == 1) {
                if(System.currentTimeMillis() - exitTime > 2000){
                    Snackbar.make(getRootView() , "再按一次退出程序" , Snackbar.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                }else{
                    finish();
                    System.exit(0);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
