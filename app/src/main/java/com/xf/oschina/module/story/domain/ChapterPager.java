package com.xf.oschina.module.story.domain;

/**
 * Author   :xf
 * Description:章节页面
 */

public class ChapterPager {
    private int chapterId;
    private int pageIndex;
    private String body;

    public ChapterPager(int chapterId, int pageIndex, String body) {
        this.chapterId = chapterId;
        this.pageIndex = pageIndex;
        this.body = body;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
