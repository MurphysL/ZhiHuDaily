package com.murphysl.zhihudaily.api;

import com.murphysl.zhihudaily.bean.BeforeNewsBean;
import com.murphysl.zhihudaily.bean.DetailNews;
import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.bean.LongCommentsBean;
import com.murphysl.zhihudaily.bean.ShortCommentsBean;
import com.murphysl.zhihudaily.bean.SplashImgBean;
import com.murphysl.zhihudaily.bean.ThemeNewsBean;
import com.murphysl.zhihudaily.bean.ThemesBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * CommonApi
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:48
 */

public interface CommonApi {

    @GET("7/prefetch-launch-images/1080*1776")
    Observable<SplashImgBean> getSplashImg();

    @GET("4/news/latest")
    Observable<LatestNewsBean> getLatestNews();

    @GET("4/news/before/{date}")
    Observable<BeforeNewsBean> getBeforeNews(@Path("date") String date);

    @GET("4/news/{id}")
    Observable<DetailNews> getDetailNews(@Path("id") int id);

    @GET("4/themes")
    Observable<ThemesBean> getThemesBean();

    @GET("4/theme/{theme}")
    Observable<ThemeNewsBean> getThemeNewsBean(@Path("theme") int theme);

    @GET("4/story/{id}/long-comments")
    Observable<LongCommentsBean> getLongCommentsBean(@Path("id") int id);

    @GET("4/story/{id}/short-comments")
    Observable<ShortCommentsBean> getShortCommentsBean(@Path("id") int id);
}
