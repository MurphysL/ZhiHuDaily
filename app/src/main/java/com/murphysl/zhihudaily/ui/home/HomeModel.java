package com.murphysl.zhihudaily.ui.home;

import com.murphysl.zhihudaily.api.Network;
import com.murphysl.zhihudaily.bean.BeforeNewsBean;
import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.mvpframe.base.BaseModel;
import com.murphysl.zhihudaily.mvpframe.rx.RxSchedulerHelper;

import io.reactivex.Observable;

/**
 * HomeModel
 *
 * @author: MurphySL
 * @time: 2017/1/30 18:55
 */


public class HomeModel implements HomeContract.Model {

    @Override
    public Observable<LatestNewsBean> getLatestNews() {
        return Network.getInstance().getCommonApi().getLatestNews()
                .compose(RxSchedulerHelper.<LatestNewsBean>io_main());
    }

    @Override
    public Observable<BeforeNewsBean> getBeforeNews(String date) {
        return Network.getInstance().getCommonApi().getBeforeNews(date)
                .compose(RxSchedulerHelper.<BeforeNewsBean>io_main());
    }
}
