package com.murphysl.zhihudaily.function.theme;

import com.murphysl.zhihudaily.data.bean.ThemeNewsBean;
import com.murphysl.zhihudaily.base.mvp.BaseModel;
import com.murphysl.zhihudaily.base.mvp.BasePresenter;
import com.murphysl.zhihudaily.base.mvp.BaseView;

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
