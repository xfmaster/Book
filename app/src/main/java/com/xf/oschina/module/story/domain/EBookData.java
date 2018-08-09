package com.xf.oschina.module.story.domain;

import java.util.List;

public class EBookData {
    private int total;
    private List<EBookComment> reviews;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<EBookComment> getReviews() {
        return reviews;
    }

    public void setReviews(List<EBookComment> reviews) {
        this.reviews = reviews;
    }
}
