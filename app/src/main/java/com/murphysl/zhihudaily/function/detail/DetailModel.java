package com.murphysl.zhihudaily.function.detail;

import com.murphysl.zhihudaily.data.api.Network;
import com.murphysl.zhihudaily.data.bean.DetailNews;
import com.murphysl.zhihudaily.util.rx.RxSchedulerHelper;

import io.reactivex.Observable;

/**
 * DetailModel
 *
 * @author: MurphySL
 * @time: 2017/1/30 19:39
 */


public class DetailModel implements DetailContract.Model {
    @Override
    public Observable<DetailNews> getDetailNews(int id) {
        return Network.getInstance().getCommonApi().getDetailNews(id)
                .compose(RxSchedulerHelper.<DetailNews>io_main());
    }

}
