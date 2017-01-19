package com.murphysl.zhihudaily.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.murphysl.zhihudaily.mvpframe.base.BaseFragment;
import com.murphysl.zhihudaily.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * SplashFragment
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:46
 */


public abstract class SplashFragment extends BaseFragment<SplashModel , SplashPresenter> implements SplashContract.View {

    @BindView(R.id.contentFrame)
    ImageView contentFrame;

    public SplashFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.splash_frag, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.getSplashImg();
    }

    @Override
    public void showImg(String img) {
        Picasso.with(getContext())
                .load(img)
                .centerCrop()
                .into(contentFrame);
    }
}
