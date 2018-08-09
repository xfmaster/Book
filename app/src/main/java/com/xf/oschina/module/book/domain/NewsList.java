package com.xf.oschina.module.book.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class NewsList {
    /**
     * id : 26754
     * author : test33
     * pubDate : 2013-09-17 16:49:50.0
     * title : asdfa
     * authorid : 253469
     * commentCount : 0
     * type : 4
     */

    private int id;
    private String author;
    private String pubDate;
    private String title;
    private int authorid;
    private int commentCount;
    private int type;

    @Generated(hash = 533867912)
    public NewsList(int id, String author, String pubDate, String title,
            int authorid, int commentCount, int type) {
        this.id = id;
        this.author = author;
        this.pubDate = pubDate;
        this.title = title;
        this.authorid = authorid;
        this.commentCount = commentCount;
        this.type = type;
    }

    @Generated(hash = 1806220618)
    public NewsList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
