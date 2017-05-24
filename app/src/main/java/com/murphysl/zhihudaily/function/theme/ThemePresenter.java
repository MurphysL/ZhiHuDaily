package com.murphysl.zhihudaily.function.theme;

import com.murphysl.zhihudaily.data.bean.ThemeNewsBean;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * ThemePresenter
 *
 * @author: MurphySL
 * @time: 2017/2/6 15:53
 */


public class ThemePresenter extends ThemeContract.Presenter {
    @Override
    void getThemeNews(int id) {
        rx.add(model.getThemeNews(id).subscribe(
                new Consumer<ThemeNewsBean>() {
                    @Override
                    public void accept(ThemeNewsBean themeNewsBean) throws Exception {
                        view.showThemeNews(themeNewsBean);
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
