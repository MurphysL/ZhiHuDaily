package com.murphysl.zhihudaily.ui.main;

import com.murphysl.zhihudaily.bean.ThemesBean;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * MainPresenter
 *
 * @author: MurphySL
 * @time: 2017/1/26 11:34
 */


public class MainPresenter extends MainContract.Presenter {

    @Override
    void getThemes() {
        rx.add(model.getThemes().subscribe(
                new Consumer<ThemesBean>() {
                    @Override
                    public void accept(ThemesBean themesBean) throws Exception {
                        view.showThemes(themesBean);
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.onRequestError(throwable.toString());
                    }
                },
                new Action() {
                    @Override
                    public void run() throws Exception {
                        view.onRequestEnd();
                    }
                }
        ));
    }

}
