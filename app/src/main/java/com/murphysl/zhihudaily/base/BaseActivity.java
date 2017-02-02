package com.murphysl.zhihudaily.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.murphysl.zhihudaily.R;

/**
 * BaseActivity
 *
 * @author: MurphySL
 * @time: 2017/1/28 18:53
 */


public abstract class BaseActivity extends AppCompatActivity {

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
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}