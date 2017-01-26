package com.murphysl.zhihudaily.bean;

import android.support.v7.widget.Toolbar;

/**
 * ToolBarProduct
 *
 * @author: MurphySL
 * @time: 2017/1/25 17:34
 */


public class ToolBarProduct {
    private Toolbar navigationIcon;
    private Toolbar title;

    public Toolbar getTitle() {
        return title;
    }

    public void setTitle(Toolbar title) {
        this.title = title;
    }

    public Toolbar getNavigationIcon() {
        return navigationIcon;
    }

    public void setNavigationIcon(Toolbar navigationIcon) {
        this.navigationIcon = navigationIcon;
    }
}
