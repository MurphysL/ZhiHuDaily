package com.murphysl.zhihudaily.ui.detail;


import com.murphysl.zhihudaily.bean.DetailNews;
import com.murphysl.zhihudaily.mvpframe.base.BaseModel;
import com.murphysl.zhihudaily.mvpframe.base.BasePresenter;
import com.murphysl.zhihudaily.mvpframe.base.BaseView;

import io.reactivex.Observable;

/**
 * DetailContract
 *
 * @author: MurphySL
 * @time: 2017/1/30 19:39
 */


public interface DetailContract {

    interface View extends BaseView{
        void showDetailNews(DetailNews detailBean);
    }

    interface Model extends BaseModel{
        Observable<DetailNews> getDetailNews(int id);
    }

    abstract class Presenter extends BasePresenter<Model , View>{
        abstract void getDetailNews(int id);
    }
}
