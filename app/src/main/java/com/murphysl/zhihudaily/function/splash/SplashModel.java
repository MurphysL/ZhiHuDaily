package com.murphysl.zhihudaily.function.splash;

import com.murphysl.zhihudaily.data.api.Network;
import com.murphysl.zhihudaily.data.bean.SplashImgBean;
import com.murphysl.zhihudaily.util.rx.RxSchedulerHelper;

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
