package com.murphysl.zhihudaily.data.bean;

import java.util.List;

/**
 * ShortCommentsBean
 *
 * @author: MurphySL
 * @time: 2017/2/15 11:24
 */


public class ShortCommentsBean {

    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

}
