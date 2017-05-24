package com.murphysl.zhihudaily.data.news.source;

import android.content.Context;

import com.murphysl.zhihudaily.data.news.BeforeNewsBean;
import com.murphysl.zhihudaily.data.news.source.local.BeforeNewsLocalRepository;
import com.murphysl.zhihudaily.data.news.source.remote.BeforeNewsRemoteRespository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

/**
 * BeforeNewsRepository
 *
 * author: MurphySL
 * time: 2017/5/23 19:32
 */


public class BeforeNewsRepository implements NewsDataSource<BeforeNewsBean>{

    private BeforeNewsLocalRepository local ;
    private BeforeNewsRemoteRespository remote;
    public BeforeNewsRepository(Context context){
        local = BeforeNewsLocalRepository.getINSTANCE(context);
        remote = new BeforeNewsRemoteRespository();
    }

    @Override
    public Observable<BeforeNewsBean> getNews(String date) {
        local.getNews(date);
        return null;
    }

    @Override
    public boolean saveNews(@NotNull BeforeNewsBean news) {
        return local.saveNews(news);
    }

    @Override
    public void deleteNews(@NotNull BeforeNewsBean news) {

    }
}
