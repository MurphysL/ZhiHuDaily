package com.murphysl.zhihudaily.data.news.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.murphysl.zhihudaily.util.config.DBConstantsKt;
import com.murphysl.zhihudaily.data.news.BeforeNewsBean;
import com.murphysl.zhihudaily.data.news.StoriesBean;
import com.murphysl.zhihudaily.data.news.source.NewsDataSource;
import com.murphysl.zhihudaily.util.rx.RxSchedulerHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * BeforeNewsLocalRepository
 *
 * author: MurphySL
 * time: 2017/5/24 21:24
 */


public class BeforeNewsLocalRepository implements NewsDataSource<BeforeNewsBean> {

    private DBHelper helper ;
    private static BeforeNewsLocalRepository INSTANCE;

    private BeforeNewsLocalRepository(Context context){
        helper = new DBHelper(context);
    }

    public static BeforeNewsLocalRepository getINSTANCE(Context context){
        if(INSTANCE == null){
            synchronized (BeforeNewsLocalRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new BeforeNewsLocalRepository(context);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Observable<BeforeNewsBean> getNews(final String date) {
        return Observable.create(new ObservableOnSubscribe<BeforeNewsBean>() {
            @Override
            public void subscribe(ObservableEmitter<BeforeNewsBean> e) throws Exception {
                final SQLiteDatabase db = helper.getWritableDatabase();
                String sql = "SELECT * FROM " + DBConstantsKt.getTABLE_NEWS_NAME() + " WHERE "
                        + DBConstantsKt.getNEWS_DATE() + " = ?";
                String[] selectionArgs = {date};
                Cursor cursor = db.rawQuery(sql, selectionArgs);

                BeforeNewsBean beforeNewsBean = new BeforeNewsBean();
                beforeNewsBean.setDate(date);
                List<StoriesBean> list = new ArrayList<>();
                if(cursor != null && cursor.getCount() > 0){
                    while(cursor.moveToNext()){
                        StoriesBean bean = new StoriesBean();
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBConstantsKt.getNEWS_ID()));
                        bean.setId(id);
                        String title = cursor.getString(cursor.getColumnIndexOrThrow(DBConstantsKt.getNEWS_TITLE()));
                        bean.setTitle(title);
                        byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(DBConstantsKt.getNEWS_IMAGE()));
                        int is_top = cursor.getInt(cursor.getColumnIndexOrThrow(DBConstantsKt.getNEWS_IS_TOP()));
                        list.add(bean);
                    }
                }
                beforeNewsBean.setStories(list);

                if(cursor != null)
                    cursor.close();

                db.close();
                e.onNext(beforeNewsBean);
            }
        }).compose(RxSchedulerHelper.<BeforeNewsBean>io_main());
    }

    @Override
    public boolean saveNews(@NotNull BeforeNewsBean news) {
        SQLiteDatabase db = helper.getWritableDatabase();

        String data = news.getDate();
        for(StoriesBean bean : news.getStories()){
            ContentValues values = new ContentValues();
            values.put(DBConstantsKt.getNEWS_ID(), bean.getId());
            values.put(DBConstantsKt.getNEWS_TITLE(), bean.getTitle());
            values.put(DBConstantsKt.getNEWS_DATE(), data);
            long row = db.insert(DBConstantsKt.getTABLE_NEWS_NAME(), DBConstantsKt.getNEWS_IMAGE() , values);
            if(row == -1) {
                db.close();
                return false;
            }
        }
        db.close();
        return true;
    }

    @Override
    public void deleteNews(@NotNull BeforeNewsBean news) {

    }
}
