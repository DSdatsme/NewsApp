package com.example.dsdatsme.newsapp.Utils;

public class NewsStructure {

    private final String newsType, newsSection,newsDate, newsTitle;

    public NewsStructure(String newsType,String newsSection,String newsDate, String newsTitle){
        this.newsType = newsType;
        this.newsSection = newsSection;
        this.newsDate = newsDate;
        this.newsTitle = newsTitle;
    }

    public String getNewsType() {
        return newsType;
    }

    public String getNewsSection() {
        return newsSection;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public String getNewsTitle() {
        return newsTitle;
    }
}
