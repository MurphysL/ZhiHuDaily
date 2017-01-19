package com.murphysl.zhihudaily.ui.splash;

import com.murphysl.zhihudaily.bean.SplashImgBean;
import com.murphysl.zhihudaily.mvpframe.base.BaseModel;
import com.murphysl.zhihudaily.mvpframe.base.BasePresenter;
import com.murphysl.zhihudaily.mvpframe.base.BaseView;

import io.reactivex.Observable;

/**
 * SplashContract
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:43
 */


public interface SplashContract {

    interface View extends BaseView{
        void showImg(String img);
    }

    interface Model extends BaseModel{
        Observable<SplashImgBean> getSplashImg();
    }

    abstract class Presenter extends BasePresenter<Model , View>{
        abstract void getSplashImg();
    }
}
