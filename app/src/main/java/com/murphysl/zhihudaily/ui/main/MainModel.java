package com.murphysl.zhihudaily.ui.main;

import com.murphysl.zhihudaily.api.Network;
import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.mvpframe.rx.RxSchedulerHelper;

import io.reactivex.Observable;

/**
 * MainModel
 *
 * @author: MurphySL
 * @time: 2017/1/26 11:35
 */


public class MainModel implements MainContract.Model {

    @Override
    public Observable<LatestNewsBean> getLatestNews() {
        return Network.getInstance().getCommonApi().getLatestNews()
                .compose(RxSchedulerHelper.<LatestNewsBean>io_main());
    }
}
