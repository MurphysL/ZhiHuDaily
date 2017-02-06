package com.murphysl.zhihudaily.ui.theme;

import com.murphysl.zhihudaily.api.Network;
import com.murphysl.zhihudaily.bean.ThemeNewsBean;
import com.murphysl.zhihudaily.mvpframe.rx.RxSchedulerHelper;

import io.reactivex.Observable;

/**
 * ThemeModel
 *
 * @author: MurphySL
 * @time: 2017/2/6 15:52
 */


public class ThemeModel implements ThemeContract.Model {
    @Override
    public Observable<ThemeNewsBean> getThemeNews(int id) {
        return Network.getInstance().getCommonApi().getThemeNewsBean(id)
                .compose(RxSchedulerHelper.<ThemeNewsBean>io_main());
    }
}
