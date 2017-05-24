package com.murphysl.zhihudaily.function.splash;

import com.murphysl.zhihudaily.data.bean.SplashImgBean;
import com.murphysl.zhihudaily.base.mvp.BaseModel;
import com.murphysl.zhihudaily.base.mvp.BasePresenter;
import com.murphysl.zhihudaily.base.mvp.BaseView;

import io.reactivex.Observable;

/**
 * SplashContract
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:43
 */


public interface SplashContract {

    interface View extends BaseView{
        void showImg(SplashImgBean imgBean);
    }

    interface Model extends BaseModel{
        Observable<SplashImgBean> getSplashImg();
    }

    abstract class Presenter extends BasePresenter<Model , View>{
        abstract void getSplashImg();
    }
}
