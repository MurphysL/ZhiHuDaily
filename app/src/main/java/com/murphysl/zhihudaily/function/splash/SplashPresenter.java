package com.murphysl.zhihudaily.function.splash;

import com.murphysl.zhihudaily.bean.SplashImgBean;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


/**
 * SplashPresenter
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:42
 */


public class SplashPresenter extends SplashContract.Presenter {

    @Override
    void getSplashImg() {

        rx.add( model.getSplashImg().subscribe(
                new Consumer<SplashImgBean>() {
                    @Override
                    public void accept(SplashImgBean imgBean) throws Exception {
                        view.showImg(imgBean);
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.onRequestError(throwable.toString());
                    }
                } ,
                new Action() {
                    @Override
                    public void run() throws Exception {
                        view.onRequestEnd();
                    }
                }));

    }

}
