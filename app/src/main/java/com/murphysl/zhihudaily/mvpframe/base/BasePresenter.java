package com.murphysl.zhihudaily.mvpframe.base;

import com.murphysl.zhihudaily.mvpframe.rx.RxManager;

/**
 * BasePresenter
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:14
 */


public abstract class BasePresenter<M , V> {

    public M model;
    public V view;
    public RxManager rx = new RxManager();

    public void attachMV(M model, V view){
        this.model = model;
        this.view = view;
    }

    public void detachMV(){
        rx.clear();
        model = null;
        view = null;
    }

}
