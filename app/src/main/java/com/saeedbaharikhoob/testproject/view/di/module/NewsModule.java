package com.saeedbaharikhoob.testproject.view.di.module;

import android.app.Activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ActivityNewsBinding;
import com.saeedbaharikhoob.testproject.utils.ItemText;
import com.saeedbaharikhoob.testproject.view.adapter.CommentsAdapter;
import com.saeedbaharikhoob.testproject.view.adapter.MainPagePagerAdapter;
import com.saeedbaharikhoob.testproject.view.adapter.SearchAdapter;
import com.saeedbaharikhoob.testproject.viewmodel.NewsViewModel;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */

@Module
public class NewsModule {
    Activity activity;

    public NewsModule(Activity activity) {
        this.activity = activity;


    }


    @Provides
    NewsViewModel getNewsViewModel() {
        return ViewModelProviders.of((FragmentActivity) activity).get(NewsViewModel.class);
    }

    @Provides
    ActivityNewsBinding getActivityNewsBinding() {
        return DataBindingUtil.setContentView(activity, R.layout.activity_news);
    }

    @Provides
    BandwidthMeter getBandwidthMeter() {
        return new DefaultBandwidthMeter();
    }

    @Provides
    TrackSelection.Factory getTrackSelectionFactory() {
        return new AdaptiveTrackSelection.Factory(getBandwidthMeter());
    }

    @Provides
    TrackSelector getTrackSelector() {
        return new DefaultTrackSelector(getTrackSelectionFactory());
    }

    @Provides
    LoadControl getLoadControl() {
        return new DefaultLoadControl();
    }

    @Provides
    CommentsAdapter getCommentsAdapter() {
        return new CommentsAdapter();
    }

    @Provides
    GridLayoutManager getGridLayoutManager() {
        return new GridLayoutManager(activity,1);
    }


}
