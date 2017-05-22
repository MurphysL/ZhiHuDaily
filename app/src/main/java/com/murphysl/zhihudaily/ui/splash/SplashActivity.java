package com.murphysl.zhihudaily.ui.splash;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.bean.SplashImgBean;
import com.murphysl.zhihudaily.mvpframe.base.BaseMVPActivity;
import com.murphysl.zhihudaily.mvpframe.rx.RxSchedulerHelper;
import com.murphysl.zhihudaily.ui.main.MainActivity;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * SplashActivity
 *
 * author: MurphySL
 * time: 2017/1/19 12:41
 */


public class SplashActivity extends BaseMVPActivity<SplashModel, SplashPresenter> implements SplashContract.View {

    private ImageView img;
    private ImageView logo;
    private TextView text;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        presenter.getSplashImg();
    }

    @Override
    protected void initView() {
        img = (ImageView) findViewById(R.id.splash_img);
        logo = (ImageView) findViewById(R.id.splash_logo);
        text = (TextView) findViewById(R.id.splash_text);

        Drawable drawable = logo.getDrawable();
        if(drawable instanceof Animatable)
            ((Animatable) drawable).start();
    }

    @Override
    public void showImg(final SplashImgBean imgBean) {
        if(imgBean == null)
            return;
        Logger.i("SplashImgBean" + imgBean.toString());

        Observable.timer(500 ,TimeUnit.MILLISECONDS)
                .compose(RxSchedulerHelper.<Long>io_main())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        Picasso.with(SplashActivity.this)
                                .load(imgBean.getCreatives().get(0).getUrl())
                                .fit()
                                .into(img);

                        text.setText("");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void startNextActivity(){
        Observable.timer(3 , TimeUnit.SECONDS)
                .compose(RxSchedulerHelper.<Long>io_main())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        startActivity(new Intent(SplashActivity.this , MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onRequestError(String msg) {
        Logger.i("onRequestError: " + msg);

        startNextActivity();
    }

    @Override
    public void onRequestEnd() {
        startNextActivity();
    }

}
