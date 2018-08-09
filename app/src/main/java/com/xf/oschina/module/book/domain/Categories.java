package com.xf.oschina.module.book.domain;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Categories {
    @Id
    @SerializedName("_id")
    private String id;//": "57c83777421aa97cbd81c74d",
    @SerializedName("en_name")
    private String en_name;//":"wow",
    @SerializedName("name")
    private String name;//":"科技资讯",
    @SerializedName("rank")
    private int rank;//1

    @Generated(hash = 1544045927)
    public Categories(String id, String en_name, String name, int rank) {
        this.id = id;
        this.en_name = en_name;
        this.name = name;
        this.rank = rank;
    }

    @Generated(hash = 267348489)
    public Categories() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

