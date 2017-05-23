package com.murphysl.zhihudaily.data.source;

import com.murphysl.zhihudaily.data.News;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * NewsDataSource
 * <p>
 * author: MurphySL
 * time: 2017/5/23 19:31
 */


public interface NewsDataSource {

    interface LoadNewsCallback{
        void onNewsLoaded(List<News> news);

        void onNewsNotAvailable();
    }

    interface GetNewsCallback{
        void onNewsLoaded(News news);

        void onNewsNotAvailable();
    }

    void getNews(@NotNull LoadNewsCallback callback);

    void saveNews(@NotNull News news);

    void deleteNews(@NotNull News news);
}
