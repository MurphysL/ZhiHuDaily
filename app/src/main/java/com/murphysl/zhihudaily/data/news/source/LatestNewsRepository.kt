package com.murphysl.zhihudaily.data.news.source

import com.murphysl.zhihudaily.data.news.BeforeNewsBean
import com.murphysl.zhihudaily.data.news.LatestNewsBean
import io.reactivex.Observable

/**
 * LatestNewsBean
 *
 * author: MurphySL
 * time: 2017/5/24 20:44
 */
class LatestNewsRepository : NewsDataSource<LatestNewsBean> {
    override fun getNews(date: String?): Observable<BeforeNewsBean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun saveNews(news: LatestNewsBean): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteNews(news: LatestNewsBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
