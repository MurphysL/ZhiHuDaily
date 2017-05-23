package com.murphysl.zhihudaily.function.detail;

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
import com.murphysl.zhihudaily.function.comment.CommentActivity;
import com.murphysl.zhihudaily.mvpframe.base.BaseMVPActivity;
import com.murphysl.zhihudaily.util.HtmlManager;
import com.murphysl.zhihudaily.util.SharedPreferencesUtils;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;


/**
 * DetailActivity
 *
 * js
 * 标题缩写
 * 赞与评论数
 * 共享元素失效
 *
 * @author: MurphySL
 * @time: 2017/1/30 20:06
 */


public class DetailActivity extends BaseMVPActivity<DetailModel, DetailPresenter> implements DetailContract.View{

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

        SharedPreferencesUtils sp = new SharedPreferencesUtils(this);
        String skinModel = sp.getSuffix();
        if(skinModel.equals(Constants.SKIN_SUFFIX)){
            collapsingToolbarLayout.setContentScrim(getDrawable(R.color.colorPrimaryDark));
        }
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
                Intent intent = new Intent(this , CommentActivity.class);
                intent.putExtra(Constants.NEWS_ID_COMMENTS, newsId);
                startActivity(intent);
        }
        return true;
    }

    @Override
    protected void handleIntent(Intent intent) {
        newsId = intent.getIntExtra(Constants.NEWS_ID, 0);
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

        Logger.i(detailBean.getCss().get(0));

        HtmlManager utils = new HtmlManager(this);
        utils.setOnDataChangeListener(detailBean.getBody() , detailBean.getCss().get(0) , new HtmlManager.HtmlDataChangeListener() {
            @Override
            public void changed(final String data) {
                Logger.i("showDetailNews: " + data);
                webview.post(new Runnable() {
                    @Override
                    public void run() {
                        webview.loadDataWithBaseURL(null ,data , HtmlManager.MIME_TYPE, HtmlManager.ENCODING,null);
                    }
                });

            }
        });

    }

}
