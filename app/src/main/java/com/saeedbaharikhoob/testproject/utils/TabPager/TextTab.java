package com.saeedbaharikhoob.testproject.utils.TabPager;

import android.graphics.Point;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.utils.TabHost;


public class TextTab extends RecyclerView.ViewHolder {

    public ImageView image;
    public LinearLayout layoutTab;
    private Point lastTouchedPoint;
    public TextView title;
    public LinearLayout strip;
    public View view;

    TabHost host;
    private int pos;

    public TextTab(View itemView) {
        super(itemView);

        image = (ImageView) itemView.findViewById(R.id.image);
        layoutTab = (LinearLayout) itemView.findViewById(R.id.layoutTab);
        title = (TextView) itemView.findViewById(R.id.title);
        strip = (LinearLayout) itemView.findViewById(R.id.strip);
        view = (View) itemView.findViewById(R.id.view);

    }



    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public void setLayoutTab(LinearLayout layoutTab) {
        this.layoutTab = layoutTab;
    }

    public LinearLayout getLayoutTab() {
        return layoutTab;
    }

    public LinearLayout getStrip() {
        return strip;
    }

    public void setStrip(LinearLayout strip) {
        this.strip = strip;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
