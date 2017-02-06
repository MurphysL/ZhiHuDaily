package com.murphysl.zhihudaily.bean;

import java.util.List;

/**
 * EditorsBean
 *
 * @author: MurphySL
 * @time: 2017/2/6 19:57
 */


public class EditorsBean implements NewsBean{

    /**
     * url : http://www.zhihu.com/people/wezeit
     * bio : 微在 Wezeit 主编
     * id : 70
     * avatar : http://pic4.zhimg.com/068311926_m.jpg
     * name : 益康糯米
     */

    private String url;
    private String bio;
    private int id;
    private List<String> avatars;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<String> avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EditorsBean{" +
                "url='" + url + '\'' +
                ", bio='" + bio + '\'' +
                ", id=" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
