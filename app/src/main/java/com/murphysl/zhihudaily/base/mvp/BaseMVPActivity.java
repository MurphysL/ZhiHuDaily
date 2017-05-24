package com.murphysl.zhihudaily.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.murphysl.zhihudaily.base.BaseActivity;
import com.murphysl.zhihudaily.util.TUtil;

/**
 * BaseMVPActivity
 *
 * @author: MurphySL
 * @time: 2017/1/30 16:28
 */


public abstract class BaseMVPActivity<M extends BaseModel , P extends BasePresenter> extends BaseActivity implements BaseView{

    protected M model;
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = TUtil.getT(this, 1);
        model = TUtil.getT(this, 0);
        if(this instanceof BaseView)
            presenter.attachMV(model , this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null)
            presenter.detachMV();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected abstract void initView();

    @Override
    public abstract void onRequestEnd();

    @Override
    public abstract void onRequestError(String msg) ;

}
