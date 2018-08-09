package com.xf.oschina.module.book.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("error")
    private boolean error;//": false
    @SerializedName("results")
    private List<Categories> results;//":

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Categories> getResults() {
        return results;
    }

    public void setResults(List<Categories> results) {
        this.results = results;
    }
}
