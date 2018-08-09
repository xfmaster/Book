package com.xf.oschina.module.book.domain;

public class Comment {
    /**
     * rating : {"max":5,"value":"5","min":0}
     * votes : 294
     * author : {"name":"薛亦晴","is_banned":false,"is_suicide":false,"url":"https://api.douban.com/v2/user/3137358","avatar":"https://img1.doubanio.com/icon/u3137358-27.jpg","uid":"3137358","alt":"https://www.douban.com/people/3137358/","type":"user","id":"3137358","large_avatar":"https://img1.doubanio.com/icon/up3137358-27.jpg"}
     * title : 海水尚有涯，相思永无岸
     * updated : 2017-07-24 13:07:52
     * comments : 20
     * summary :        八年前，在古代文学基础课的课堂上听老师分析《古诗十九首》。听他细细分析“行行重行行，与君生别离”一句中绵密的相思与相隔两地想见而不...
     * useless : 8
     * published : 2013-12-03 07:58:38
     * alt : https://book.douban.com/review/6444618/
     * id : 6444618
     */

    private RatingBean rating;
    private int votes;
    private AuthorBean author;
    private String title;
    private String updated;
    private int comments;
    private String summary;
    private int useless;
    private String published;
    private String alt;
    private String id;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getUseless() {
        return useless;
    }

    public void setUseless(int useless) {
        this.useless = useless;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class RatingBean {
        /**
         * max : 5
         * value : 5
         * min : 0
         */

        private int max;
        private String value="";
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class AuthorBean {
        /**
         * name : 薛亦晴
         * is_banned : false
         * is_suicide : false
         * url : https://api.douban.com/v2/user/3137358
         * avatar : https://img1.doubanio.com/icon/u3137358-27.jpg
         * uid : 3137358
         * alt : https://www.douban.com/people/3137358/
         * type : user
         * id : 3137358
         * large_avatar : https://img1.doubanio.com/icon/up3137358-27.jpg
         */

        private String name;
        private boolean is_banned;
        private boolean is_suicide;
        private String url;
        private String avatar;
        private String uid;
        private String alt;
        private String type;
        private String id;
        private String large_avatar;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isIs_banned() {
            return is_banned;
        }

        public void setIs_banned(boolean is_banned) {
            this.is_banned = is_banned;
        }

        public boolean isIs_suicide() {
            return is_suicide;
        }

        public void setIs_suicide(boolean is_suicide) {
            this.is_suicide = is_suicide;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLarge_avatar() {
            return large_avatar;
        }

        public void setLarge_avatar(String large_avatar) {
            this.large_avatar = large_avatar;
        }
    }
}
