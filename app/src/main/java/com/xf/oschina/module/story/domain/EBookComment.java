package com.xf.oschina.module.story.domain;

public class EBookComment {
    /**
     * _id : 56fd44e030c892ad31e11a14
     * rating : 5
     * author : {"_id":"5397f76502d04bae28389325","avatar":"/avatar/03/9c/039c953fe4e38e2cb6b0ae07d34ca231","nickname":"渡劫失败掉到","activityAvatar":"","type":"normal","lv":8,"gender":"male"}
     * helpful : {"no":303,"total":654,"yes":957}
     * likeCount : 86
     * state : normal
     * updated : 2018-07-18T18:29:49.517Z
     * created : 2016-03-31T15:40:16.527Z
     * commentCount : 380
     * content : 跟随主角一路走来，从九霄大陆，到真武神域，发现他陪他的家人时间很少，就算有也是寥寥几笔，友情有意之人，却为了实力，从而忽略了他的亲人，犹如有意识的僵尸，只顾提升实力，我想当他现在七界之颠，赫然发现红颜已旧，亲已老，虽然实力固然重要，但到头来才发现自己守护的那片土地，那些人，都悄然不在，给了安宁的日子，以及衣食无忧的生活，却给不了正真的亲情以及爱情，唉😔～～～～事在人为，身不由己啊。
     * 蜡炬已残泪难干
     * 江山未老红颜旧
     * 子欲孝而亲不在
     * ～～～～～
     * title : 值得你收藏的一本好书
     */

    private String _id;
    private int rating;
    private AuthorBean author;
    private HelpfulBean helpful;
    private int likeCount;
    private String state;
    private String updated;
    private String created;
    private int commentCount;
    private String content;
    private String title;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public HelpfulBean getHelpful() {
        return helpful;
    }

    public void setHelpful(HelpfulBean helpful) {
        this.helpful = helpful;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class AuthorBean {
        /**
         * _id : 5397f76502d04bae28389325
         * avatar : /avatar/03/9c/039c953fe4e38e2cb6b0ae07d34ca231
         * nickname : 渡劫失败掉到
         * activityAvatar :
         * type : normal
         * lv : 8
         * gender : male
         */

        private String _id;
        private String avatar;
        private String nickname;
        private String activityAvatar;
        private String type;
        private int lv;
        private String gender;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getActivityAvatar() {
            return activityAvatar;
        }

        public void setActivityAvatar(String activityAvatar) {
            this.activityAvatar = activityAvatar;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getLv() {
            return lv;
        }

        public void setLv(int lv) {
            this.lv = lv;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

    public static class HelpfulBean {
        /**
         * no : 303
         * total : 654
         * yes : 957
         */

        private int no;
        private int total;
        private int yes;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getYes() {
            return yes;
        }

        public void setYes(int yes) {
            this.yes = yes;
        }
    }
}
