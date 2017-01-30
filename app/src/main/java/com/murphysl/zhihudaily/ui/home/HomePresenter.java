package com.murphysl.zhihudaily.ui.home;

import com.murphysl.zhihudaily.bean.LatestNewsBean;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * HomePresenter
 *
 * @author: MurphySL
 * @time: 2017/1/30 18:55
 */


public class HomePresenter extends HomeContract.Presenter {
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
