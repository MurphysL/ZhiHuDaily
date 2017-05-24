package com.murphysl.zhihudaily.data.news.source.remote;

import com.murphysl.zhihudaily.data.api.Network;
import com.murphysl.zhihudaily.data.news.BeforeNewsBean;
import com.murphysl.zhihudaily.data.news.LatestNewsBean;
import com.murphysl.zhihudaily.util.rx.RxSchedulerHelper;


import io.reactivex.Observable;

/**
 * LatestNewsRemoteRespository
 *
 * author: MurphySL
 * time: 2017/5/24 22:22
 */


public class LatestNewsRemoteRespository {
    public Observable<LatestNewsBean> getNews() {
        return Network.getInstance().getCommonApi().getLatestNews()
                .compose(RxSchedulerHelper.<LatestNewsBean>io_main());
    }
}
