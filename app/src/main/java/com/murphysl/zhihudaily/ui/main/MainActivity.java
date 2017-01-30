package com.murphysl.zhihudaily.ui.main;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.MultiItemTypeAdapter;
import com.murphysl.zhihudaily.adapter.delegate.ThemesDelegate;
import com.murphysl.zhihudaily.adapter.wrapper.HeaderAndFooterWrapper;
import com.murphysl.zhihudaily.base.BaseFragment;
import com.murphysl.zhihudaily.bean.ThemesBean;
import com.murphysl.zhihudaily.mvpframe.base.MVPActivity;
import com.murphysl.zhihudaily.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends MVPActivity<MainModel , MainPresenter> implements MainContract.View{
    private static final String TAG = "MainActivity";

    private Toolbar toolbar;
    FrameLayout content;
    private DrawerLayout drawer;
    private RecyclerView recyclerView;
    private RelativeLayout head;
    private HeaderAndFooterWrapper wrapper;

    private List<ThemesBean.OthersBean> bean = new ArrayList<>();

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        toolbar.setNavigationIcon(R.drawable.menu);
        //setToolBar(toolbar , R.string.toolbar_title_main);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawer.isDrawerOpen(Gravity.LEFT)){
                    drawer.closeDrawers();
                }else{
                    drawer.openDrawer(Gravity.LEFT);
                }
            }
        });

        MultiItemTypeAdapter<ThemesBean.OthersBean> adapter = new MultiItemTypeAdapter<>(this , bean);
        adapter.addItemViewDelegate(new ThemesDelegate());
        wrapper = new HeaderAndFooterWrapper(adapter);
        head = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.drawer_head , null);
        wrapper.addHeaderView(head);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(wrapper);
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return new HomeFragment();
    }

    @Override
    protected void initData() {
        presenter.getThemes();
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onRequestError(String msg) {
        Log.i(TAG, "onRequestError: " + msg);
    }

    @Override
    public void showThemes(ThemesBean themesBean) {
        if(themesBean != null){
            Log.i(TAG, "showThemes: " + themesBean.toString());
            for(int i = 0 ;i < themesBean.getOthers().size() ;i ++){
                bean.add(themesBean.getOthers().get(i));
            }
            wrapper.notifyDataSetChanged();
        }else{
            Log.i(TAG, "showThemes: " + "请求错误");
        }
    }
}
