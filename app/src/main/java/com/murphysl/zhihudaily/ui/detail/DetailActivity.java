package com.murphysl.zhihudaily.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;

import com.murphysl.zhihudaily.Constants;
import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.bean.DetailNews;
import com.murphysl.zhihudaily.mvpframe.base.MVPActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * DetailActivity
 *
 * @author: MurphySL
 * @time: 2017/1/30 20:06
 */


public class DetailActivity extends MVPActivity<DetailModel, DetailPresenter> implements DetailContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.img)
    ImageView img;

    private int newsId = -1;

    @Override
    protected void initView() {
        webview = (WebView) findViewById(R.id.webview);
        img = (ImageView) findViewById(R.id.img);
    }

    @Override
    protected void handleIntent(Intent intent) {
        newsId = intent.getExtras().getInt(Constants.newsId);
    }

    @Override
    protected void initData() {
        if(newsId != -1)
            presenter.getDetailNews(newsId);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_detail;
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void showDetailNews(DetailNews detailBean) {
        if(detailBean != null){
            Picasso.with(this)
                    .load(detailBean.getImage())
                    .into(img);
        }
    }

}
