package com.murphysl.zhihudaily.ui.splash;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.ImageView;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.bean.SplashImgBean;
import com.murphysl.zhihudaily.mvpframe.base.MVPActivity;
import com.murphysl.zhihudaily.mvpframe.rx.RxSchedulerHelper;
import com.murphysl.zhihudaily.ui.main.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * SplashActivity
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:41
 */


public class SplashActivity extends MVPActivity<SplashModel, SplashPresenter> implements SplashContract.View {
    private static final String TAG = "SplashActivity";

    private ImageView img;

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
    }

    @Override
    public void showImg(SplashImgBean imgBean) {
        Log.i(TAG, "showImg: " + imgBean.toString());
        Picasso.with(this)
                .load(imgBean.getImg())
                .fit()
                .into(img);
    }

    @Override
    public void onRequestError(String msg) {
        Log.i(TAG, "onRequestError: " + msg.toString());
        Snackbar.make(img , msg.toString() , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestEnd() {
        Observable.timer(2 , TimeUnit.SECONDS)
                .compose(RxSchedulerHelper.<Long>io_main())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        startActivity(new Intent(SplashActivity.this , MainActivity.class));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
