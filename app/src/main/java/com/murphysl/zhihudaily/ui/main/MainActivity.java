package com.murphysl.zhihudaily.ui.main;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.MultiItemTypeAdapter;
import com.murphysl.zhihudaily.adapter.delegate.ThemesDelegate;
import com.murphysl.zhihudaily.adapter.wrapper.HeaderAndFooterWrapper;
import com.murphysl.zhihudaily.base.BaseFragment;
import com.murphysl.zhihudaily.bean.ThemesBean;
import com.murphysl.zhihudaily.config.Constants;
import com.murphysl.zhihudaily.mvpframe.base.MVPActivity;
import com.murphysl.zhihudaily.ui.home.HomeFragment;
import com.murphysl.zhihudaily.ui.skin.SkinManager;
import com.murphysl.zhihudaily.ui.theme.ThemeFragment;
import com.murphysl.zhihudaily.util.SharedPreferencesUtils;
import com.orhanobut.logger.Logger;

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

    private SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils(this);

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        toolbar.setNavigationIcon(R.drawable.navigation);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.menu));
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
        final MenuItem switchModel = toolbar.getMenu().findItem(R.id.switch_model);
        final String model = sharedPreferencesUtils.getSuffix();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.switch_model:
                        Logger.i("model" + model);
                        if(model.equals(Constants.SKIN_SUFFIX_KEY)){
                            switchModel.setTitle("日间模式");
                            SkinManager.getInstance().changeSkin("");
                        }else{
                            switchModel.setTitle("夜间模式");
                            SkinManager.getInstance().changeSkin("_dark");
                        }
                        Snackbar.make(toolbar , "更换皮肤" , Snackbar.LENGTH_SHORT).show();

                        break;
                }
                return false;
            }
        });

        MultiItemTypeAdapter<ThemesBean.OthersBean> adapter = new MultiItemTypeAdapter<>(this , bean);
        adapter.addItemViewDelegate(new ThemesDelegate());
        wrapper = new HeaderAndFooterWrapper(adapter);
        head = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.drawer_head , null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        head.setLayoutParams(layoutParams);
        wrapper.addHeaderView(head);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ThemeFragment fragment = new ThemeFragment();
                fragment.themeId = bean.get(position).getId();
                addFragment(fragment);

                drawer.closeDrawers();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
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
