package com.xf.oschina.module.story.domain;

import java.util.List;

/**
 * Author   :hymanme
 * Email    :hymanme@163.com
 * Create at 2016/9/29
 * Description:
 */

public class BookChapter {


    private MixToc mixToc;

    public MixToc getMixToc() {
        return mixToc;
    }

    public void setMixToc(MixToc mixToc) {
        this.mixToc = mixToc;
    }

    public static class MixToc {
        private String _id;
        //bookId
        private String book;
        private String chaptersUpdated;
        private String updated;
        /**
         * title : 1，相亲
         * link : http://novel.hongxiu.com/a/1218893/12118325.html
         * unreadble : false
         */

        private List<Chapters> chapters;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getBook() {
            return book;
        }

        public void setBook(String book) {
            this.book = book;
        }

        public String getChaptersUpdated() {
            return chaptersUpdated;
        }

        public void setChaptersUpdated(String chaptersUpdated) {
            this.chaptersUpdated = chaptersUpdated;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public List<Chapters> getChapters() {
            return chapters;
        }

        public void setChapters(List<Chapters> chapters) {
            this.chapters = chapters;
        }

        public static class Chapters {
            private String title;
            private String link;
            private boolean unreadble;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public boolean isUnreadble() {
                return unreadble;
            }

            public void setUnreadble(boolean unreadble) {
                this.unreadble = unreadble;
            }
        }
    }
}
