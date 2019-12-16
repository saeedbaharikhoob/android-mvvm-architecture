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
import com.saeedbaharikhoob.testproject.databinding.ActivityRegisterBinding;
import com.saeedbaharikhoob.testproject.view.adapter.CommentsAdapter;
import com.saeedbaharikhoob.testproject.viewmodel.NewsViewModel;
import com.saeedbaharikhoob.testproject.viewmodel.RegisterViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */

@Module
public class RegisterModule {
    Activity activity;

    public RegisterModule(Activity activity) {
        this.activity = activity;


    }
    @Provides
    ActivityRegisterBinding getActivityNewsBinding() {
        return DataBindingUtil.setContentView(activity, R.layout.activity_register);
    }


    @Provides
    RegisterViewModel getNewsViewModel() {
        return ViewModelProviders.of((FragmentActivity) activity).get(RegisterViewModel.class);
    }



}
