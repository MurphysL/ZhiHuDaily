package com.murphysl.zhihudaily.mvpframe.base;

/**
 * BasePresenter
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:14
 */


public abstract class BasePresenter<M , V> {

    public M model;
    public V view;

    public void attachVM(V view , M model){
        this.model = model;
        this.view = view;
    }

    public void detachVM(){
        model = null;
        view = null;
    }

}
