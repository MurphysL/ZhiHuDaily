package com.murphysl.zhihudaily.function.detail;


import com.murphysl.zhihudaily.data.bean.DetailNews;
import com.murphysl.zhihudaily.base.mvp.BaseModel;
import com.murphysl.zhihudaily.base.mvp.BasePresenter;
import com.murphysl.zhihudaily.base.mvp.BaseView;

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
