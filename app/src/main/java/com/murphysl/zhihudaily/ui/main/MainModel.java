package com.murphysl.zhihudaily.ui.main;

import com.murphysl.zhihudaily.api.Network;
import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.bean.ThemesBean;
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
    public Observable<ThemesBean> getThemes() {
        return Network.getInstance().getCommonApi().getThemesBean()
                .compose(RxSchedulerHelper.<ThemesBean>io_main());
    }
}
