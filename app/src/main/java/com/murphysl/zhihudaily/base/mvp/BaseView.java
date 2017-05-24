package com.murphysl.zhihudaily.base.mvp;

/**
 * BaseView
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:12
 */


public interface BaseView {

    void onRequestError(String msg);
    void onRequestEnd();
}
