package com.saeedbaharikhoob.testproject.view.di.module;

import android.app.Activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
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
import com.saeedbaharikhoob.testproject.databinding.ActivityStatePostsBinding;
import com.saeedbaharikhoob.testproject.view.adapter.SearchAdapter;
import com.saeedbaharikhoob.testproject.viewmodel.NewsViewModel;
import com.saeedbaharikhoob.testproject.viewmodel.StatePostsViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */

@Module
public class StatePostsModule {
    Activity activity;

    public StatePostsModule(Activity activity) {
        this.activity = activity;


    }


    @Provides
    GridLayoutManager getGridLayoutManager() {
        return new GridLayoutManager(activity,1);
    }

    @Provides
    SearchAdapter getSearchAdapter() {
        return new SearchAdapter();
    }

    @Provides
    StatePostsViewModel getNewsViewModel() {
        return ViewModelProviders.of((FragmentActivity) activity).get(StatePostsViewModel.class);
    }

    @Provides
    ActivityStatePostsBinding getActivityNewsBinding() {
        return DataBindingUtil.setContentView(activity, R.layout.activity_state_posts);
    }



}
