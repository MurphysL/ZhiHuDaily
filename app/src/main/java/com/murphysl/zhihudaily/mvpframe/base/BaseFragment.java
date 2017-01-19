package com.murphysl.zhihudaily.mvpframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.murphysl.zhihudaily.mvpframe.util.TUtil;

/**
 * BaseFragment
 *
 * @author: MurphySL
 * @time: 2017/1/19 14:39
 */


public class BaseFragment<M extends BaseModel , P extends BasePresenter> extends Fragment implements BaseView{

    protected M model;
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = TUtil.getT(this, 0);
        model = TUtil.getT(this, 1);
        if(this instanceof BaseView)
            presenter.attachVM(this , model);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null)
            presenter.detachVM();
    }

    protected void initData(){ }

}
