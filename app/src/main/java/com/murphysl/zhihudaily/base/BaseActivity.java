package com.murphysl.zhihudaily.base;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * BaseActivity
 *
 * @author: MurphySL
 * @time: 2017/1/28 18:53
 */


public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getContentViewId();

    protected abstract int getFragmentViewId();

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
