package com.xf.oschina.module.story.domain;

import java.io.Serializable;

/**
 * Author   :xf
 * Description:章节类
 */

public class ChapterRead {

    private Chapter chapter;

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public static class Chapter implements Serializable {
        private int chapterId;
        private String title;
        private String body;
        private String cpContent;

        public Chapter(String title, String body) {
            this.title = title;
            this.body = body;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public Chapter(String title, String body, String cpContent) {
            this.title = title;
            this.body = body;
            this.cpContent = cpContent;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCpContent() {
            return cpContent;
        }

        public void setCpContent(String cpContent) {
            this.cpContent = cpContent;
        }
    }
}
