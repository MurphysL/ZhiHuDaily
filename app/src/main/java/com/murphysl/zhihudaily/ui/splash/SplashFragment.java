package com.murphysl.zhihudaily.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.murphysl.zhihudaily.ui.main.MainActivity;
import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.bean.SplashImgBean;
import com.murphysl.zhihudaily.mvpframe.base.BaseFragment;
import com.murphysl.zhihudaily.mvpframe.rx.RxSchedulerHelper;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * SplashFragment
 *
 * logo动画
 * picasso缓存
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:46
 */


public class SplashFragment extends BaseFragment<SplashModel, SplashPresenter> implements SplashContract.View {
    private static final String TAG = "SplashFragment";

    private ImageView contentFrame;

    public SplashFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.splash_frag, container, false);
        contentFrame = (ImageView) root.findViewById(R.id.contentFrame);
        return root;
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.getSplashImg();
    }

    @Override
    public void showImg(SplashImgBean imgBean) {
        Log.i(TAG, "showImg: " + imgBean.toString());
        Picasso.with(getContext())
                .invalidate(imgBean.getImg());
        Picasso.with(getContext())
                .load(imgBean.getImg())
                .fit()
                .into(contentFrame);
    }

    @Override
    public void onRequestEnd() {
        super.onRequestEnd();
        Observable.timer(2 , TimeUnit.SECONDS)
                .compose(RxSchedulerHelper.<Long>io_main())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        startActivity(new Intent(getActivity() , MainActivity.class));
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
