package com.murphysl.zhihudaily.data.news.source;

import android.content.Context;
import android.test.mock.MockContext;

import com.murphysl.zhihudaily.data.news.StoriesBean;

import org.junit.Test;

/**
 * NewsBeanDBManagerTest
 * <p>
 * author: MurphySL
 * time: 2017/5/23 18:38
 */

public class NewsBeanDBManagerTest {
    @Test
    public void getDataBase() throws Exception {

    }

    @Test
    public void insertStorie() throws Exception {
        Context context = new MockContext();
        StoriesBean bean = new StoriesBean();
        bean.setId(0);
        bean.setTitle("Test");
        NewsDBManager.getDataBase(context).insertStorie(bean);
    }

}