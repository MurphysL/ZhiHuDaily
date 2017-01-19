package com.murphysl.zhihudaily.api;

import com.murphysl.zhihudaily.BuildConfig;
import com.murphysl.zhihudaily.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

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

    private OkHttpClient configClient(){
        OkHttpClient.Builder okhttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT , TimeUnit.SECONDS);
        return okhttpClient.build();
    }

    private <T> T configRetrofit(Class<T> service){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(service);
    }

}
