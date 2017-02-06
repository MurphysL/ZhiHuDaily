package com.murphysl.zhihudaily.ui.theme;

import com.murphysl.zhihudaily.bean.ThemeNewsBean;
import com.murphysl.zhihudaily.mvpframe.base.BaseModel;
import com.murphysl.zhihudaily.mvpframe.base.BasePresenter;
import com.murphysl.zhihudaily.mvpframe.base.BaseView;

import io.reactivex.Observable;

/**
 * ThemeContract
 *
 * @author: MurphySL
 * @time: 2017/2/6 15:50
 */


public interface ThemeContract {

    interface View extends BaseView{
        void showThemeNews(ThemeNewsBean themeNewsBean);
    }

    interface Model extends BaseModel{
        Observable<ThemeNewsBean> getThemeNews(int id);
    }

    abstract class Presenter extends BasePresenter<Model , View>{
        abstract void getThemeNews(int id);
    }
}
