package com.murphysl.zhihudaily.ui.splash;

import com.murphysl.zhihudaily.api.Network;
import com.murphysl.zhihudaily.bean.SplashImgBean;
import com.murphysl.zhihudaily.mvpframe.rx.RxSchedulerHelper;

import io.reactivex.Observable;

/**
 * SplashModel
 *
 * @author: MurphySL
 * @time: 2017/1/19 17:23
 */


public class SplashModel implements SplashContract.Model {
    @Override
    public Observable<SplashImgBean> getSplashImg() {
        return Network.getInstance().getCommonApi().getSplashImg()
                .compose(RxSchedulerHelper.<SplashImgBean>io_main());
    }
}
