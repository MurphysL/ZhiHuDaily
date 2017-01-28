package com.murphysl.zhihudaily.ui.drawer;

import com.murphysl.zhihudaily.bean.ThemesBean;
import com.murphysl.zhihudaily.mvpframe.base.BaseModel;
import com.murphysl.zhihudaily.mvpframe.base.BasePresenter;
import com.murphysl.zhihudaily.mvpframe.base.BaseView;
import com.murphysl.zhihudaily.ui.splash.SplashContract;

import io.reactivex.Observable;

/**
 * DrawerContract
 *
 * @author: MurphySL
 * @time: 2017/1/28 18:15
 */


public interface DrawerContract {

    interface View extends BaseView{
        void showThemes(ThemesBean bean);
    }

    interface Model extends BaseModel{
        Observable<ThemesBean> getThemes();
    }

    abstract class Presenter extends BasePresenter<Model , View>{
        abstract void getThemes();
    }
}
