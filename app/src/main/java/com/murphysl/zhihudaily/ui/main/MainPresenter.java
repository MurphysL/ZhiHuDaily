package com.murphysl.zhihudaily.ui.main;

import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.mvpframe.base.BasePresenter;

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
    void getLatestNews() {
        rx.add(model.getLatestNews().subscribe(
                new Consumer<LatestNewsBean>() {
                    @Override
                    public void accept(LatestNewsBean latestNewsBean) throws Exception {
                        view.showLatestNews(latestNewsBean);
                    }
                }
                , new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.onRequestError(throwable.toString());
                    }
                }
                , new Action() {
                    @Override
                    public void run() throws Exception {
                        view.onRequestEnd();
                    }
                }
        ));
    }

}
