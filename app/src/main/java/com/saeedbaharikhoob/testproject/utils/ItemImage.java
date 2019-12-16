package com.saeedbaharikhoob.testproject.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.network.Urls;

/**
 * Created by Saeed on 5/19/2015.
 */
public class ItemImage {
    private String image;
    private Holder holder;





    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof Holder)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.item_image, null);


            holder = new Holder();
            oldView.setTag(holder);
            getItem(context, holder, oldView);
            return oldView;
        } else {
            holder = (Holder) oldView.getTag();
            getItem(context, holder, oldView);
            return oldView;
        }
    }

    private void getItem(final Context context, final Holder holder, final View view) {
        try {
            holder.itemImage = this;
            if (holder.imageView == null) {
                holder.imageView = view.findViewById(R.id.imageView);
            }

            Glide.with(context)
                    .load(Urls.Product_IMAGE_URL+getImage())
                    .into(holder.imageView);

        } catch (Exception e) {

        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public class Holder {
        ImageView imageView;
        ItemImage itemImage;

    }


}
