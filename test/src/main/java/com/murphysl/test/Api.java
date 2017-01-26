package com.murphysl.test;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Api
 *
 * @author: MurphySL
 * @time: 2017/1/24 16:02
 */


public interface Api {
    @GET("start-image/1080*1776")
    Call<SplashImgBean> getImg();

    @GET("news/latest")
    Call<LatestNewsBean> getLatestNews();
}
