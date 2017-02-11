package com.murphysl.zhihudaily.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.ui.skin.SkinAttr;
import com.murphysl.zhihudaily.ui.skin.SkinAttrSupport;
import com.murphysl.zhihudaily.ui.skin.SkinChangeListener;
import com.murphysl.zhihudaily.ui.skin.SkinManager;
import com.murphysl.zhihudaily.ui.skin.SkinView;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BaseActivity
 *
 * @author: MurphySL
 * @time: 2017/1/28 18:53
 */


public abstract class BaseActivity extends AppCompatActivity implements SkinChangeListener{

    private long exitTime;

    private Method createViewMethod = null;

    private final Object[] mConstructorArgs = new Object[2];
    private final Object[] mCreateViewArgs = new Object[4];
    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.view.",
            "android.webkit."
    };
    private static final Map<String, Constructor<? extends View>> sConstructorMap
            = new ArrayMap<>();
    private static final Class<?>[] sConstructorSignature = new Class[]{
            Context.class, AttributeSet.class};
    private static final Class<?>[] sCreateViewSignature = new Class[]{
            View.class , String.class , Context.class , AttributeSet.class};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SkinManager.getInstance().registerListener(this);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflaterCompat.setFactory(layoutInflater, new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                AppCompatDelegate delegate = getDelegate();
                View view = null;
                List<SkinAttr> skinAttrs = null;
                try {
                    if(createViewMethod == null)
                        createViewMethod = delegate.getClass().getMethod("createView" ,sCreateViewSignature);

                    mCreateViewArgs[0] = parent;
                    mCreateViewArgs[1] = name;
                    mCreateViewArgs[2] = context;
                    mCreateViewArgs[3] = attrs;

                    view = (View) createViewMethod.invoke(delegate , mCreateViewArgs);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                skinAttrs = SkinAttrSupport.getSkinAttrs(attrs , context);
                if(skinAttrs.isEmpty())
                    return view;

                if(view == null){
                    view = createViewFromTag(context , name , attrs);
                }
                if(view != null){
                    injectSkin(view , skinAttrs);
                }
                return view;
            }
        });
        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());
        initView();
    }

    private void injectSkin(View view, List<SkinAttr> skinAttrs) {
        Logger.i("injectSkin");
        List<SkinView> skinViews = SkinManager.getInstance().getSkinViews(this);
        if(skinViews == null){
            skinViews = new ArrayList<>();
            SkinManager.getInstance().addSkinViews(this , skinViews);
        }

        skinViews.add(new SkinView(view , skinAttrs));

        if (SkinManager.getInstance().needChangeSkin()) {
            SkinManager.getInstance().skinChange(this);
        }

    }

    private View createViewFromTag(Context context, String name, AttributeSet attrs) {
        if (name.equals("view")) {
            name = attrs.getAttributeValue(null, "class");
        }

        try {
            mConstructorArgs[0] = context;
            mConstructorArgs[1] = attrs;

            if (-1 == name.indexOf('.')) {
                for (int i = 0; i < sClassPrefixList.length; i++) {
                    final View view = createView(context, name, sClassPrefixList[i]);
                    if (view != null) {
                        return view;
                    }
                }
                return null;
            } else {
                return createView(context, name, null);
            }
        } catch (Exception e) {
            // We do not want to catch these, lets return null and let the actual LayoutInflater
            // try
            return null;
        } finally {
            // Don't retain references on context.
            mConstructorArgs[0] = null;
            mConstructorArgs[1] = null;
        }
    }

    private View createView(Context context, String name, String prefix)
            throws ClassNotFoundException, InflateException {
        Constructor<? extends View> constructor = sConstructorMap.get(name);

        try {
            if (constructor == null) {
                // Class not found in the cache, see if it's real, and try to add it
                Class<? extends View> clazz = context.getClassLoader().loadClass(
                        prefix != null ? (prefix + name) : name).asSubclass(View.class);

                constructor = clazz.getConstructor(sConstructorSignature);
                sConstructorMap.put(name, constructor);
            }
            constructor.setAccessible(true);
            return constructor.newInstance(mConstructorArgs);
        } catch (Exception e) {
            // We do not want to catch these, lets return null and let the actual LayoutInflater
            // try
            return null;
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(getIntent() != null){
            handleIntent(getIntent());
        }
        if(getSupportFragmentManager() != null){
            BaseFragment firstFragment = getFirstFragment();
            if(firstFragment != null)
                addFragment(firstFragment);
        }
        initData();
    }

    @Override
    protected void onDestroy() {
        SkinManager.getInstance().unRegisterListener(this);
        super.onDestroy();
    }

    protected void initData(){ }

    protected abstract void initView();

    protected void handleIntent (Intent intent){ }

    protected View getRootView(){
        return this.getWindow().getDecorView();
    }

    protected int getContentViewId(){ return R.layout.activity_main;}

    protected int getFragmentViewId(){ return R.id.content;}

    protected BaseFragment getFirstFragment(){ return null;}

    protected void addFragment(BaseFragment fragment){
        if(fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentViewId() , fragment , fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();//允许异常丢失时可以正常提交
        }
    }

    protected void popFragment(){
        if(getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStack();
        }else{
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(KeyEvent.KEYCODE_BACK == keyCode){
            if(getSupportFragmentManager().getBackStackEntryCount() == 1) {
                if(System.currentTimeMillis() - exitTime > 2000){
                    Snackbar.make(getRootView() , "再按一次退出程序" , Snackbar.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                }else{
                    finish();
                    System.exit(0);
                }
                return true;
            }else{
                popFragment();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void OnSkinChange() { }
}
