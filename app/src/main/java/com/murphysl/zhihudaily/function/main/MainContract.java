package com.murphysl.zhihudaily.function.main;

import com.murphysl.zhihudaily.data.bean.ThemesBean;
import com.murphysl.zhihudaily.base.mvp.BaseModel;
import com.murphysl.zhihudaily.base.mvp.BasePresenter;
import com.murphysl.zhihudaily.base.mvp.BaseView;

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
