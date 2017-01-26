package com.murphysl.zhihudaily.api;

import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.bean.SplashImgBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

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


}
