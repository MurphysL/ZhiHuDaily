package com.murphysl.zhihudaily.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

/**
 * YToolBar
 *
 * @author: MurphySL
 * @time: 2017/1/25 17:49
 */


public class MurphySToolBar extends Toolbar {

    private MurphySToolBar.Builder builder;

    protected MurphySToolBar(Context context) {
        this(context , null);
    }

    protected MurphySToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs , 0);
    }

    protected MurphySToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static class Builder{
        private Context context;
        private MurphySToolBar murphySToolBar;
        private MurphySToolBar.Builder navigationIcon;
        private MurphySToolBar.Builder title;

        public Builder(Context context) {
            this.context = context;
            murphySToolBar = new MurphySToolBar(context);
        }

        public MurphySToolBar.Builder setNavigationIcon(int icon) {
            murphySToolBar.setNavigationIcon(icon);
            return this;
        }

        public MurphySToolBar.Builder setTitle(@StringRes  int title) {
            murphySToolBar.setTitle(title);
            return this;
        }

        public MurphySToolBar build(){
            return murphySToolBar;
        }

    }
}
