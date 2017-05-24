package com.murphysl.zhihudaily.data.news;



import com.murphysl.zhihudaily.data.NewsBean;

import java.util.List;

/**
 * StoriesBean
 *
 * author: MurphySL
 * time: 2017/2/4 19:05
 */


public class StoriesBean implements NewsBean {

    /**
     * title : 中国古代家具发展到今天有两个高峰，一个两宋一个明末（多图）
     * ga_prefix : 052321
     * images : ["http://p1.zhimg.com/45/b9/45b9f057fc1957ed2c946814342c0f02.jpg"]
     * type : 0
     * id : 3930445
     */

    private String title;
    private String ga_prefix;
    private int type;
    private int id;
    private List<String> images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "StoriesBean{" +
                "title='" + title + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", images=" + images +
                '}';
    }
}
