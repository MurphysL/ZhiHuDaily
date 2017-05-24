package com.murphysl.zhihudaily.function.home;

import com.murphysl.zhihudaily.data.news.BeforeNewsBean;
import com.murphysl.zhihudaily.data.news.LatestNewsBean;
import com.murphysl.zhihudaily.base.mvp.BaseModel;
import com.murphysl.zhihudaily.base.mvp.BasePresenter;
import com.murphysl.zhihudaily.base.mvp.BaseView;

import io.reactivex.Observable;

/**
 * HomeContract
 *
 * @author: MurphySL
 * @time: 2017/1/30 18:55
 */


public interface HomeContract{
    interface View extends BaseView{
        void showLatestNews(LatestNewsBean latestNewsBean);
        void showBeforeNews(BeforeNewsBean beforeNewsBean);
    }

    interface Model extends BaseModel{
        Observable<LatestNewsBean> getLatestNews();
        Observable<BeforeNewsBean> getBeforeNews(String date);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getLatestNews();
        abstract void getBeforeNews(String date);
    }
}
