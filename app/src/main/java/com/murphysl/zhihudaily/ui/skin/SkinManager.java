package com.murphysl.zhihudaily.ui.skin;

import android.content.Context;

import com.murphysl.zhihudaily.util.SharedPreferencesUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SkinManager
 *
 * @author: MurphySL
 * @time: 2017/2/9 17:16
 */

public class SkinManager {

    private Context context;
    private SharedPreferencesUtils spUtils;

    private List<SkinChangeListener> listeners = new ArrayList<>();
    private Map<SkinChangeListener , List<SkinView>> skinViewMaps = new HashMap<>();

    private String suffix;

    private static SkinManager ourInstance ;

    public static SkinManager getInstance() {
        if(ourInstance == null){
            synchronized (SkinManager.class){
                if(ourInstance == null){
                    ourInstance = new SkinManager();
                }
            }
        }
        return ourInstance;
    }

    private SkinManager(){

    }

    public void init(Context context){
        this.context = context.getApplicationContext();
        spUtils = new SharedPreferencesUtils(context);

        suffix = spUtils.getSuffix();
        Logger.i("suffix  " + suffix);
    }

    public ResourcesManager getResourcesManager() {
        return new ResourcesManager(context.getResources() , context.getPackageName() , suffix);
    }


    public List<SkinView> getSkinViews(SkinChangeListener listener){
        return skinViewMaps.get(listener);
    }

    public void addSkinViews(SkinChangeListener listener , List<SkinView> views){
        skinViewMaps.put(listener , views);
    }

    public void changeSkin(String suffix){
        clearPluginInfo();
        this.suffix = suffix;

        spUtils.saveSuffix(suffix);
        notifyChangeListener();


    }

    private void notifyChangeListener() {
        for(SkinChangeListener listener : listeners){
            skinChange(listener);
            listener.OnSkinChange();
        }
    }

    public void skinChange(SkinChangeListener listener) {
        List<SkinView> skinViews = skinViewMaps.get(listener);
        for(SkinView skinView : skinViews)
            skinView.apply();
    }

    private void clearPluginInfo() {
        suffix = null;
        spUtils.clear();
    }

    public void registerListener(SkinChangeListener listener){
        listeners.add(listener);
    }

    public void unRegisterListener(SkinChangeListener listener){
        listeners.remove(listener);
        skinViewMaps.remove(listener);
    }

    public boolean needChangeSkin() {
        return suffix != null && !suffix.equals("");
    }
}
