package com.murphysl.zhihudaily.data.news.source.remote;

import com.murphysl.zhihudaily.data.api.Network;
import com.murphysl.zhihudaily.data.news.BeforeNewsBean;
import com.murphysl.zhihudaily.data.news.source.NewsDataSource;
import com.murphysl.zhihudaily.util.rx.RxSchedulerHelper;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

/**
 * BeforeNewsRemoteRespository
 *
 * author: MurphySL
 * time: 2017/5/24 21:53
 */


public class BeforeNewsRemoteRespository {

    public Observable<BeforeNewsBean> getNews(@NotNull String date) {
        return Network.getInstance().getCommonApi().getBeforeNews(date)
                .compose(RxSchedulerHelper.<BeforeNewsBean>io_main());
    }

}
