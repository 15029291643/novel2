package com.example.rxjava.logic.model;

import java.util.List;

public class Novel {
    private String title;
    private String url;
    private List<Chapter> catalog;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Chapter> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<Chapter> catalog) {
        this.catalog = catalog;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", catalog=" + catalog +
                '}';
    }
}
