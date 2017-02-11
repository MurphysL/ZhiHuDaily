package com.murphysl.zhihudaily.ui.skin;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

/**
 * ResourcesManager
 *
 * @author: MurphySL
 * @time: 2017/2/9 17:36
 */


public class ResourcesManager {

    private Resources resources;
    private String pkgName;
    private String suffix;

    public ResourcesManager(Resources resources, String pkgName , String suffix) {
        this.resources = resources;
        this.pkgName = pkgName;

        if(suffix == null)
            suffix = "";
        this.suffix = suffix;
    }

    public Drawable getDrawableByResName(String name){
        try{
            name = appendSuffix(name);
            return resources.getDrawable(resources.getIdentifier(name , "drawable" , pkgName));
        }catch (Resources.NotFoundException e) {
            try {
                return resources.getDrawable(resources.getIdentifier(name, "color", pkgName));
            } catch (Resources.NotFoundException e2) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public ColorStateList getColorByResName(String name){
        Logger.i("getColorByResName  " + name);
        name = appendSuffix(name);
        Logger.i("getColorByResNameSuffix  " + name);
        try {
            return resources.getColorStateList(resources.getIdentifier(name , "color" , pkgName));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String appendSuffix(String name) {
        if(!TextUtils.isEmpty(name)){
            name += suffix;
        }
        return name;
    }
}
