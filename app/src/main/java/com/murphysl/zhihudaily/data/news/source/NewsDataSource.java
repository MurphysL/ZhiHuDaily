package com.murphysl.zhihudaily.data.news.source;

import com.murphysl.zhihudaily.data.news.BeforeNewsBean;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

/**
 * NewsDataSource
 *
 * author: MurphySL
 * time: 2017/5/23 19:31
 */


public interface NewsDataSource<T> {
    Observable<BeforeNewsBean> getNews(String date);

    boolean saveNews(@NotNull T news);

    void deleteNews(@NotNull T news);
}
