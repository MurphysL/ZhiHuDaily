package com.murphysl.zhihudaily.api;

import com.murphysl.zhihudaily.bean.DetailNews;
import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.bean.SplashImgBean;
import com.murphysl.zhihudaily.bean.ThemesBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * CommonApi
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:48
 */


public interface CommonApi {

    @GET("start-image/1080*1776")
    Observable<SplashImgBean> getSplashImg();

    @GET("news/latest")
    Observable<LatestNewsBean> getLatestNews();

    @GET("news/{id}")
    Observable<DetailNews> getDetailNews(@Path("id") int id);

    @GET("themes")
    Observable<ThemesBean> getThemesBean();


}
