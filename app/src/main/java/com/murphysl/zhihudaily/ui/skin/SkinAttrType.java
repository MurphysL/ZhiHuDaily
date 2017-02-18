package com.murphysl.zhihudaily.ui.skin;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

/**
 * SkinAttrType
 *
 * @author: MurphySL
 * @time: 2017/2/9 17:09
 */


public enum SkinAttrType {

    BACKGROUND("background") {
        @Override
        void apply(View view, String resName) {
            Drawable drawable = getResourcesManager().getDrawableByResName(resName);
            if(drawable != null)
                view.setBackground(drawable);
        }
    },
    SRC("src") {
        @Override
        void apply(View view, String resName) {
            Drawable drawable = getResourcesManager().getDrawableByResName(resName);
            if(view instanceof ImageView)
                if(drawable != null)
                    ((ImageView) view).setImageDrawable(drawable);
        }
    },
    TEXT_COLOR("textColor") {
        @Override
        void apply(View view, String resName) {
            ColorStateList colorStateList = getResourcesManager().getColorByResName(resName);
            if(view instanceof TextView)
                if(colorStateList != null)
                    ((TextView) view).setTextColor(colorStateList);
        }
    };

    private String resType;
    SkinAttrType(String resType){
        this.resType = resType;
    }

    abstract void apply(View view ,String resName);

    public String getResType() {
        return resType;
    }

    private static ResourcesManager getResourcesManager(){
        return SkinManager.getInstance().getResourcesManager();
    }


}
