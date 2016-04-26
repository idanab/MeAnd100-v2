package com.example.android.meand100_v2.news;

import android.graphics.Bitmap;

/**
 * Created by Idan on 27/01/2016.
 */
public class FullStory {
    final String article;
    final String summary;
    final Bitmap picture;

    public FullStory(String article, String summary, Bitmap picture) {
        this.article = article;
        this.summary = summary;
        this.picture = picture;
    }

    public String getArticle() {
        return article;
    }

    public String getSummary() {
        return summary;
    }

    public Bitmap getPicture() {
        return picture;
    }
}
