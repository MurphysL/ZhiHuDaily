package com.murphysl.zhihudaily.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

/**
 * BaseFragment
 *
 * @author: MurphySL
 * @time: 2017/1/28 18:57
 */


public abstract class BaseFragment extends Fragment {

    protected BaseActivity baseActivity;

    protected abstract void initView(View view , Bundle saveInstanceState);

    protected abstract void initData();

    protected abstract int getLayoutId();

    protected BaseActivity getBaseActivity(){
        return baseActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.baseActivity = (BaseActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i("onCreateView");
        View root = inflater.inflate(getLayoutId() , container , false);
        initView(root , savedInstanceState);
        initData();
        return root;
    }


}
