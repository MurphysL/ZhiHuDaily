package com.murphysl.zhihudaily.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.murphysl.zhihudaily.config.DBConstantsKt;
import com.murphysl.zhihudaily.data.StoriesBean;
import com.orhanobut.logger.Logger;

/**
 * NewsDBManager
 *
 * 1.数据库关闭
 * 2.图片存储
 *
 * author: MurphySL
 * time: 2017/5/23 18:08
 */


public class NewsDBManager {
    private static NewsDBManager INSTANCE;
    private static SQLiteDatabase db;

    private NewsDBManager(){}

    public static NewsDBManager getDataBase(Context context){
        if(INSTANCE == null){
            synchronized (NewsDBManager.class){
                if(INSTANCE == null){
                    INSTANCE = new NewsDBManager();
                    db = new DBHelper(context).getWritableDatabase();
                }
            }
        }
        return INSTANCE;
    }

    public boolean insertStorie(StoriesBean storiesBean){
        if(storiesBean == null)
            return false;

        ContentValues values = new ContentValues();
        values.put(DBConstantsKt.getNEWS_ID(), storiesBean.getId());
        values.put(DBConstantsKt.getNEWS_TITLE() , storiesBean.getTitle());
        //values.put(DBConstantsKt.get);

        String sql = "INSERT INTO " + DBConstantsKt.getTABLE_NEWS_NAME()
                + " (" + DBConstantsKt.getNEWS_ID() + ", "
                + DBConstantsKt.getNEWS_TITLE() + ") "
                + "VALUES (" + storiesBean.getId() + ", "
                + storiesBean.getTitle() + ")";
        Logger.i(sql);

        db.execSQL(sql);
       /* db.insert(DBConstantsKt.getTABLE_NEWS_NAME(),
                DBConstantsKt.getNEWS_IMAGE(),
                )*/
        return true;
    }

}
