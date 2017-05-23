package com.murphysl.zhihudaily.function.detail;

import com.murphysl.zhihudaily.bean.DetailNews;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * DetailPresenter
 *
 * @author: MurphySL
 * @time: 2017/1/30 19:44
 */


public class DetailPresenter extends DetailContract.Presenter {
    @Override
    void getDetailNews(int id) {
        rx.add(model.getDetailNews(id).subscribe(
                new Consumer<DetailNews>() {
                    @Override
                    public void accept(DetailNews detailNews) throws Exception {
                        view.showDetailNews(detailNews);
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
