package com.murphysl.zhihudaily.data.bean;

import java.util.List;

/**
 * LongCommentsBean
 *
 * @author: MurphySL
 * @time: 2017/2/15 10:59
 */


public class LongCommentsBean {

    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "LongCommentsBean{" +
                "comments=" + comments.toString() +
                '}';
    }
}
