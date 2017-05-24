package com.murphysl.zhihudaily.data.api;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.murphysl.zhihudaily.util.config.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Network
 *
 * @author: MurphySL
 * @time: 2017/1/19 13:25
 */


public class Network {
    private static final int DEFAULT_TIMEOUT = 5;

    private static Network mNetwork;

    private static CommonApi commonApi;

    private static Retrofit retrofit;

    public static Network getInstance(){
        if(mNetwork == null)
            mNetwork = new Network();
        return mNetwork;
    }

    public CommonApi getCommonApi(){
        return commonApi == null ? configRetrofit(CommonApi.class) : commonApi;
    }

    public OkHttpClient configClient(){
        OkHttpClient.Builder okhttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT , TimeUnit.SECONDS);
        return okhttpClient.build();
    }

    private <T> T configRetrofit(Class<T> service){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }


}
