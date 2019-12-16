package com.saeedbaharikhoob.testproject.utils;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.saeedbaharikhoob.testproject.model.retromodel.NewsRetro;
import com.saeedbaharikhoob.testproject.utils.SliderPack.ParallaxPagerTransformer;
import com.saeedbaharikhoob.testproject.utils.SliderPack.SliderView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SliderViews  extends ViewPager {
    private SliderView.SliderFragmentAdapter mAdapter;
    private Timer swipeTimer;
    private int currentPage = 0;
    private Handler handler;
    public static final int DELAY = 5000;


    public SliderViews(Context context) {
        super(context);
        init();
    }

    public SliderViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public SliderView.SliderFragmentItem getCurrentSlider() {

        return mAdapter.getFragment(getCurrentItem());
    }

    /**
     * init the view
     */
    private void init() {

        final GestureDetector tapGestureDetector = new GestureDetector(getContext(), new SliderView.TapGestureListener());

        this.setOnTouchListener((v, event) -> {
            tapGestureDetector.onTouchEvent(event);
            return false;
        });


    }

    public int getPos(int position) {
        return mAdapter.getPos(position);
    }


    public void setCustomAdapter(SliderView.SliderFragmentAdapter adapter) {
        if (adapter != null) {
            mAdapter = adapter;
            setAdapter(mAdapter);
            setCurrentItem(0);
            setPageTransformer(false, new ParallaxPagerTransformer(mAdapter.items));
        }
    }

    public void setDefaultPage(int page) {
        currentPage = page;
        setCurrentItem(page);
    }

    public void removeAllImage() {
        if (mAdapter != null)
            mAdapter.items.clear();
    }

    public void pageSwitcherManual() {
        if (mAdapter != null) {
            if (currentPage == mAdapter.getCount() - 1) {
                currentPage = 0;
            }
            setCurrentItem(currentPage++);
        }
    }

    public void pageSwitcher() {
        if (swipeTimer != null)
            swipeTimer.cancel();
        if (handler != null)
            handler.removeCallbacks(Update);

        handler = new Handler();
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 15000, 15000);


    }

    public void StoppageSwitcher() {
        swipeTimer.cancel();
        handler.removeCallbacks(Update);
    }

    Runnable Update = new Runnable() {
        public void run() {

            setCurrentItem(currentPage++);
            if (currentPage == mAdapter.getCount()) {
                currentPage = 0;
            }
        }


    };

    public int getSliderPosition() {
        return currentPage;
    }


    /**
     * for touch and click of slideshow
     */
    public static class TapGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {


            return false;
        }

    }

    /**
     * the fragment for each slide
     */
    public static class SliderFragmentAdapter extends FragmentPagerAdapter {
        private NewsRetro newsRetro;
        public ArrayList<SliderView.SliderFragmentItem> items;
        private final boolean endless;

        ArrayList<SliderView.SliderFragment> frags = new ArrayList<>();

        public SliderFragmentAdapter(FragmentManager fm, ArrayList<SliderView.SliderFragmentItem> items, boolean endless) {
            super(fm);
            this.items = items;
            this.endless = endless;
        }

        public SliderFragmentAdapter(FragmentManager fm, ArrayList<SliderView.SliderFragmentItem> items, boolean endless, NewsRetro newsRetro) {
            super(fm);
            this.items = items;
            this.endless = endless;
            this.newsRetro = newsRetro;
        }

        @Override
        public Fragment getItem(int position) {

            int pos = getPos(position);

            SliderView.SliderFragment frag = items.get(pos).getInstance(pos);

            return ((Fragment) frag);
        }

        public SliderView.SliderFragmentItem getFragment(int position) {

            int pos = getPos(position);

            return items.get(pos);
        }

        public int getPos(int position) {
            return position % items.size();
        }

        @Override
        public int getCount() {


            return items.size();
        }


        public NewsRetro getNewsRetro() {
            return newsRetro;
        }

        public void setNewsRetro(NewsRetro newsRetro ) {
            this.newsRetro = newsRetro;
        }
    }

    public interface SliderFragment {

        void loadContent();

    }


    public interface SliderFragmentItem {

        SliderView.SliderFragment getFragment();

        SliderView.SliderFragment getInstance(int pos);

        int getViewToParallaxResourceId();


    }
}
