package com.murphysl.zhihudaily.ui.drawer;

import com.murphysl.zhihudaily.api.CommonApi;
import com.murphysl.zhihudaily.api.Network;
import com.murphysl.zhihudaily.bean.ThemesBean;
import com.murphysl.zhihudaily.mvpframe.rx.RxManager;
import com.murphysl.zhihudaily.mvpframe.rx.RxSchedulerHelper;

import io.reactivex.Observable;

/**
 * DrawerModel
 *
 * @author: MurphySL
 * @time: 2017/1/28 18:15
 */


public class DrawerModel implements DrawerContract.Model {
    @Override
    public Observable<ThemesBean> getThemes() {
        return Network.getInstance().getCommonApi().getThemesBean()
                .compose(RxSchedulerHelper.<ThemesBean>io_main());
    }
}
