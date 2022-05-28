package com.example.rxjava.logic.model;

import java.util.List;

public class Chapter {
    private String title;
    private String url;
    private List<String> content;

    public Chapter() {
    }

    public Chapter(String title, String url) {
        this.title = title;
        this.url = url;
    }

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

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", content=" + content +
                '}';
    }
}
