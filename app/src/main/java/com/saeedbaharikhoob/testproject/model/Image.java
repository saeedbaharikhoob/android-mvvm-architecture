package com.saeedbaharikhoob.testproject.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.saeedbaharikhoob.testproject.network.Urls;


public class Image {
    private String type;
    private String filename;

    public String getType() {
        return type;
    }

    public String getFilename() {
        return filename;
    }
}
