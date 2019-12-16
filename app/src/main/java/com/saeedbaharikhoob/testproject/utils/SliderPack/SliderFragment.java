package com.saeedbaharikhoob.testproject.utils.SliderPack;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.network.Urls;


public class SliderFragment extends Fragment implements SliderView.SliderFragment {


    private Slider sliderFragmentItem;
    private ImageView imageView;
    private ProgressWheel progress;

    public SliderFragment() {

    }

    @SuppressLint("ValidFragment")
    public SliderFragment(Slider sliderFragmentItem) {

        this.sliderFragmentItem = sliderFragmentItem;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.slide, container, false);
        imageView =  view.findViewById(R.id.image);
        progress =  view.findViewById(R.id.progress);


        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

    @Override
    public void onStart() {
        super.onStart();

        loadContent();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadContent();
    }

    @Override
    public void loadContent() {
        try {

                Glide.with(getView().getContext())
                        .load(Urls.Product_IMAGE_URL + sliderFragmentItem.getUrl())
                        .apply(new RequestOptions()
                                .dontAnimate()
                                .fitCenter())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .into(imageView);



        } catch (Exception e) {

        }
    }


}




