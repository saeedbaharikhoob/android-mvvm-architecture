package com.saeedbaharikhoob.testproject.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.saeedbaharikhoob.testproject.network.Urls;


public class Post {
   private int id;
   private String title;
   private String image;
   private String writerName;

    public Post(int id, String title, String image, String writerName) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.writerName = writerName;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getWriterName() {
        return writerName;
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(Urls.POST_IMAGE_THUMB_URL+url)
                .into(view);
    }
}
