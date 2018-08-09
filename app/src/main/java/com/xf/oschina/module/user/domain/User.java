package com.xf.oschina.module.user.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    private String id;
    private String name;
    private String userHeader;
    private String phone;
    private String login;
    private String avatarUrl;
    private String company;
    private String reposUrl;
    private String blog;


    @Generated(hash = 1443595888)
    public User(String id, String name, String userHeader, String phone,
            String login, String avatarUrl, String company, String reposUrl,
            String blog) {
        this.id = id;
        this.name = name;
        this.userHeader = userHeader;
        this.phone = phone;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.company = company;
        this.reposUrl = reposUrl;
        this.blog = blog;
    }

    @Generated(hash = 586692638)
    public User() {
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserHeader() {
        return userHeader;
    }

    public void setUserHeader(String userHeader) {
        this.userHeader = userHeader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
