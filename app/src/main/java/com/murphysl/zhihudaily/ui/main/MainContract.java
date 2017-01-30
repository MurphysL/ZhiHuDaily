package com.murphysl.zhihudaily.ui.main;

import com.murphysl.zhihudaily.bean.ThemesBean;
import com.murphysl.zhihudaily.mvpframe.base.BaseModel;
import com.murphysl.zhihudaily.mvpframe.base.BasePresenter;
import com.murphysl.zhihudaily.mvpframe.base.BaseView;

import io.reactivex.Observable;

/**
 * MainContract
 *
 * @author: MurphySL
 * @time: 2017/1/26 11:34
 */


public interface MainContract {

    interface View extends BaseView{
        void showThemes(ThemesBean themesBean);
    }

    interface Model extends BaseModel{
        Observable<ThemesBean> getThemes();
    }

    abstract class Presenter extends BasePresenter<Model , View>{
        abstract void getThemes();
    }

}
