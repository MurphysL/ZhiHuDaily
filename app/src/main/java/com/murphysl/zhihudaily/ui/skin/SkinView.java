package com.murphysl.zhihudaily.ui.skin;

import android.view.View;

import java.util.List;

/**
 * SkinView
 *
 * @author: MurphySL
 * @time: 2017/2/9 17:29
 */


public class SkinView {

    private View view;
    private List<SkinAttr> attrs;

    public SkinView(View view, List<SkinAttr> attrs) {
        this.view = view;
        this.attrs = attrs;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<SkinAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<SkinAttr> attrs) {
        this.attrs = attrs;
    }

    public void apply(){
        for(SkinAttr attr :attrs){
            attr.apply(view);
        }
    }
}
