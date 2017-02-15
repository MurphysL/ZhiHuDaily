package com.murphysl.zhihudaily.ui.detail;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.murphysl.zhihudaily.config.Constants;
import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.bean.DetailNews;
import com.murphysl.zhihudaily.mvpframe.base.BaseMVPActivity;
import com.murphysl.zhihudaily.ui.comment.CommentActivity;
import com.murphysl.zhihudaily.util.HtmlUtils;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;


/**
 * DetailActivity
 *
 * js
 *
 * @author: MurphySL
 * @time: 2017/1/30 20:06
 */


public class DetailActivity extends BaseMVPActivity<DetailModel, DetailPresenter> implements DetailContract.View {

    private Toolbar toolbar;
    private WebView webview;
    private ImageView img;
    private TextView sourceDetail;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private int newsId = -1;

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        webview = (WebView) findViewById(R.id.webview);
        img = (ImageView) findViewById(R.id.img_detail);
        sourceDetail = (TextView) findViewById(R.id.source_detail);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbarlayout);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.comment:
                startActivity(new Intent(DetailActivity.this , CommentActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void handleIntent(Intent intent) {
        newsId = intent.getIntExtra(Constants.newsId, 0);
    }

    @Override
    protected void initData() {
        if (newsId != -1)
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
        Snackbar.make(img , msg , Snackbar.LENGTH_SHORT).show();
        Logger.w(msg);
    }

    @Override
    public void showDetailNews(DetailNews detailBean) {
        if(detailBean == null)
            return;
        Picasso.with(this)
                .load(detailBean.getImage())
                .fit()
                .into(img);
        collapsingToolbarLayout.setTitle(detailBean.getTitle());

        sourceDetail.setText(detailBean.getImage_source());

        String data = HtmlUtils.createHtmlData(detailBean.getBody() , detailBean.getCss());
        Logger.i("showDetailNews: " + data);
        webview.loadDataWithBaseURL(null ,data , HtmlUtils.MIME_TYPE, HtmlUtils.ENCODING,null);

    }

}
