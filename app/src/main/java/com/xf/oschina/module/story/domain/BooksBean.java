package com.xf.oschina.module.story.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class BooksBean {
    /**
     * _id : 5816b415b06d1d32157790b1
     * title : 圣墟
     * author : 辰东
     * shortIntro : 在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……
     * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1228859%2F1228859_d14f18e849b34420904ead54936e440a.jpg%2F
     * site : zhuishuvip
     * majorCate : 玄幻
     * minorCate : 东方玄幻
     * allowMonthly : false
     * banned : 0
     * latelyFollower : 279569
     * retentionRatio : 71.9
     */
    @Id
    private String _id;
    private String title;
    private String author;
    private String shortIntro;
    private String cover;
    private String site;
    private String majorCate;
    private String minorCate;
    private boolean allowMonthly;
    private int banned;
    private int latelyFollower;
    private String retentionRatio;
    private String parentId;

    @Generated(hash = 1982520151)
    public BooksBean(String _id, String title, String author, String shortIntro, String cover, String site, String majorCate, String minorCate,
            boolean allowMonthly, int banned, int latelyFollower, String retentionRatio, String parentId) {
        this._id = _id;
        this.title = title;
        this.author = author;
        this.shortIntro = shortIntro;
        this.cover = cover;
        this.site = site;
        this.majorCate = majorCate;
        this.minorCate = minorCate;
        this.allowMonthly = allowMonthly;
        this.banned = banned;
        this.latelyFollower = latelyFollower;
        this.retentionRatio = retentionRatio;
        this.parentId = parentId;
    }

    @Generated(hash = 396579428)
    public BooksBean() {
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortIntro() {
        return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getMajorCate() {
        return majorCate;
    }

    public void setMajorCate(String majorCate) {
        this.majorCate = majorCate;
    }

    public String getMinorCate() {
        return minorCate;
    }

    public void setMinorCate(String minorCate) {
        this.minorCate = minorCate;
    }

    public boolean isAllowMonthly() {
        return allowMonthly;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public boolean getAllowMonthly() {
        return this.allowMonthly;
    }
}