package com.murphysl.zhihudaily.ui.detail;

import com.murphysl.zhihudaily.api.Network;
import com.murphysl.zhihudaily.bean.DetailNews;
import com.murphysl.zhihudaily.mvpframe.rx.RxSchedulerHelper;

import io.reactivex.Observable;
import okhttp3.Response;

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
