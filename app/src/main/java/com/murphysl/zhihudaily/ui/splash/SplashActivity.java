package com.murphysl.zhihudaily.ui.splash;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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
 * 1、先展示logo 再出现图片
 * 2、Splash每日出现一次
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:41
 */


public class SplashActivity extends MVPActivity<SplashModel, SplashPresenter> implements SplashContract.View {
    private static final String TAG = "SplashActivity";

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
    public void showImg(SplashImgBean imgBean) {
        if(imgBean == null)
            return;
        Log.i(TAG, "showImg: " + imgBean.toString());
        Picasso.with(this)
                .load(imgBean.getImg())
                .fit()
                .into(img);
        text.setText(imgBean.getText());
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
