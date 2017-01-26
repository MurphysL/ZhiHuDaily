package com.murphysl.zhihudaily.mvpframe.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.murphysl.zhihudaily.bean.ToolBarProduct;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

/**
 * BaseActivity
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:28
 */


public class BaseActivity extends AppCompatActivity {

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initData();
        initView();
        initFragment();
        initListener();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    protected void initData(){ }

    protected void initView(){ }

    protected void initListener(){ }

    protected void initFragment(){ }

    protected void setToolBar(Toolbar toolBar , int title){
        toolBar.setTitle(this.getResources().getString(title));
    }
}
