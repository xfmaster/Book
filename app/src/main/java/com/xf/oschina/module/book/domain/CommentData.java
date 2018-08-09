package com.xf.oschina.module.book.domain;

import java.util.List;

public class CommentData {
    private List<Comment> reviews;
    private int count;
    private int total;//
    private int start;

    public List<Comment> getReviews() {
        return reviews;
    }

    public void setReviews(List<Comment> reviews) {
        this.reviews = reviews;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
