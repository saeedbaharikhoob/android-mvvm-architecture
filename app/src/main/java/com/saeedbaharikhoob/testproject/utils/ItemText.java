package com.saeedbaharikhoob.testproject.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saeedbaharikhoob.testproject.R;

import org.w3c.dom.Text;

/**
 * Created by Saeed on 5/19/2015.
 */
public class ItemText {
    private String txt;
    private Holder holder;







    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof Holder)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.item_text, null);


            holder = new Holder();
            oldView.setTag(holder);
            getItem( holder, oldView);
            return oldView;
        } else {
            holder = (Holder) oldView.getTag();
            getItem(holder, oldView);
            return oldView;
        }
    }

    private void getItem( final Holder holder, final View view) {
        try {
            holder.itemText = this;
            if (holder.textView == null) {
                holder.textView = view.findViewById(R.id.textView);
            }

            holder.textView.setText(getTxt());

        } catch (Exception e) {

        }
    }



    public class Holder {
        TextView textView;
        ItemText itemText;

    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
