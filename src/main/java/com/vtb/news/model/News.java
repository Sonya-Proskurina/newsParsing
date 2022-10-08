package com.vtb.news.model;

import javax.persistence.*;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String title;
    @Column(length = 9999999)
    private  String text;
    private String dataNews;
    private boolean important;
    private String sourceNews;
    private String viewing;

    public String getDataNews() {
        return dataNews;
    }

    public void setDataNews(String dataNews) {
        this.dataNews = dataNews;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public String getSourceNews() {
        return sourceNews;
    }

    public void setSourceNews(String sourceNews) {
        this.sourceNews = sourceNews;
    }

    public String getViewing() {
        return viewing;
    }

    public void setViewing(String viewing) {
        this.viewing = viewing;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
