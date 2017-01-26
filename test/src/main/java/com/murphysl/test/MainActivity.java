package com.murphysl.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5 , TimeUnit.SECONDS)
                .connectTimeout(5 , TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api =  retrofit.create(Api.class);
        //Call<SplashImgBean> call = api.getImg();
        Call<LatestNewsBean> call = api.getLatestNews();
        call.enqueue(new Callback<LatestNewsBean>() {
            @Override
            public void onResponse(Call<LatestNewsBean> call, Response<LatestNewsBean> response) {
                Log.i(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<LatestNewsBean> call, Throwable t) {
                Log.i(TAG, "onResponse: " + t.toString());
            }
        });
    }
}
