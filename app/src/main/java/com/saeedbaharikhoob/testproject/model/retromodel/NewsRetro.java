package com.saeedbaharikhoob.testproject.model.retromodel;

import com.saeedbaharikhoob.testproject.model.Image;
import com.saeedbaharikhoob.testproject.model.NewsDesc;

import java.util.ArrayList;


public class NewsRetro {
    private String title;
    private String writerName;
    private String media;
    private String tags;
    private boolean likeStatus;
    private ArrayList<Image> images;
    private ArrayList<NewsDesc> desc;

    public boolean isLikeStatus() {
        return likeStatus;
    }

    public String getTitle() {
        return title;
    }

    public String getMedia() {
        return media;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getTags() {
        return tags;
    }

    public ArrayList<Image> getImages() {
        return images;
    }


    public ArrayList<NewsDesc> getDesc() {
        return desc;
    }
}
