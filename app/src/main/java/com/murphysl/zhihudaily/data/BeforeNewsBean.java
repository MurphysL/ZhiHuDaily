package com.murphysl.zhihudaily.data;

import com.murphysl.zhihudaily.data.StoriesBean;

import java.util.List;

/**
 * BeforeNewsBean
 *
 * author: MurphySL
 * time: 2017/2/4 14:16
 */


public class BeforeNewsBean implements News{

    /**
     * date : 20131118
     * stories : [{"title":"深夜食堂 · 我的张曼妮","ga_prefix":"111822","images":["http://p4.zhimg.com/7b/c8/7bc8ef5947b069513c51e4b9521b5c82.jpg"],"type":0,"id":1747159},"..."]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

}
