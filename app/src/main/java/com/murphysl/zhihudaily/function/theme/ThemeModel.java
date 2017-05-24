package com.murphysl.zhihudaily.function.theme;

import com.murphysl.zhihudaily.data.api.Network;
import com.murphysl.zhihudaily.data.bean.ThemeNewsBean;
import com.murphysl.zhihudaily.util.rx.RxSchedulerHelper;

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
