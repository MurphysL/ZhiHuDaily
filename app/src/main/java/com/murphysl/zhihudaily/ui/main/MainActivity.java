package com.murphysl.zhihudaily.ui.main;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.MultiItemTypeAdapter;
import com.murphysl.zhihudaily.adapter.delegate.ThemesDelegate;
import com.murphysl.zhihudaily.adapter.wrapper.HeaderAndFooterWrapper;
import com.murphysl.zhihudaily.base.BaseFragment;
import com.murphysl.zhihudaily.bean.ThemesBean;
import com.murphysl.zhihudaily.config.Constants;
import com.murphysl.zhihudaily.mvpframe.base.BaseMVPActivity;
import com.murphysl.zhihudaily.ui.home.HomeFragment;
import com.murphysl.zhihudaily.ui.skin.SkinManager;
import com.murphysl.zhihudaily.ui.theme.ThemeFragment;
import com.murphysl.zhihudaily.util.SharedPreferencesUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.消息
 * 2.设置
 */
public class MainActivity extends BaseMVPActivity<MainModel , MainPresenter> implements MainContract.View{

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private RecyclerView recyclerView;
    private RelativeLayout head;
    private HeaderAndFooterWrapper wrapper;

    private List<ThemesBean.OthersBean> bean = new ArrayList<>();

    private SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils(this);

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        initToolbar();
        initRecyclerView();
    }

    private void initRecyclerView() {
        MultiItemTypeAdapter<ThemesBean.OthersBean> adapter = new MultiItemTypeAdapter<>(this , bean);
        adapter.addItemViewDelegate(new ThemesDelegate());
        wrapper = new HeaderAndFooterWrapper(adapter);
        head = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.drawer_head , null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        head.setLayoutParams(layoutParams);
        RelativeLayout headMain = (RelativeLayout) head.findViewById(R.id.header_home);
        headMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! (getCurrentFragment() instanceof HomeFragment))
                    popFragment();

                drawer.closeDrawers();
            }
        });
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

    private void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.navigation);
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
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.menu));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final MenuItem switchModel = toolbar.getMenu().findItem(R.id.switch_model);
        final String model = sharedPreferencesUtils.getSuffix();
        Logger.i("model" + model);
        switch (item.getItemId()){
            case R.id.switch_model:
                if(model.equals(Constants.SKIN_SUFFIX)){
                    switchModel.setTitle("日间模式");
                    SkinManager.getInstance().changeSkin("");
                }else{
                    switchModel.setTitle("夜间模式");
                    SkinManager.getInstance().changeSkin(Constants.SKIN_SUFFIX);
                }
                break;
            case R.id.msg:
                break;
            case R.id.config:
                break;
            default:
                break;
        }
        return true;
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
        Logger.w("onRequestError: " + msg);
    }


    @Override
    public void showThemes(ThemesBean themesBean) {
        if(themesBean == null)
            return;

        Logger.i("showThemes: " + themesBean.toString());
        for(int i = 0 ;i < themesBean.getOthers().size() ;i ++)
            bean.add(themesBean.getOthers().get(i));

        wrapper.notifyDataSetChanged();
    }

}
