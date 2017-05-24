package com.murphysl.zhihudaily.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.murphysl.zhihudaily.base.BaseFragment;
import com.murphysl.zhihudaily.util.TUtil;

/**
 * BaseMVPFragment
 *
 * @author: MurphySL
 * @time: 2017/1/30 16:29
 */


public abstract class BaseMVPFragment<M extends BaseModel , P extends BasePresenter> extends BaseFragment implements BaseView{

    protected M model;
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
    protected abstract void initData();

    @Override
    protected abstract void initView(View view, Bundle saveInstanceState);

    @Override
    protected abstract int getLayoutId();

    @Override
    public abstract void onRequestEnd();

    @Override
    public abstract void onRequestError(String msg);
}
