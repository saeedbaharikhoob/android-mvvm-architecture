package com.saeedbaharikhoob.testproject.view.di.module;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ActivityNewsBinding;
import com.saeedbaharikhoob.testproject.databinding.FragmentStateBinding;
import com.saeedbaharikhoob.testproject.view.adapter.StateAdapter;
import com.saeedbaharikhoob.testproject.viewmodel.StateFragmentViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */

@Module
public class StateFragmentModule {
    Activity activity;
    LayoutInflater inflater;
    ViewGroup container;
    public StateFragmentModule(Activity activity, LayoutInflater inflater, ViewGroup container) {
        this.activity = activity;
        this.inflater = inflater;
        this.container = container;

    }
    @Provides
    FragmentStateBinding getActivitysBinding() {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_state,container,false);
    }


    @Provides
    GridLayoutManager getGridLayoutManager() {
        return new GridLayoutManager(activity,1);
    }

    @Provides
    StateAdapter getStateAdapter() {
        return new StateAdapter();
    }

    @Provides
    StateFragmentViewModel getStateViewModell() {
        return  ViewModelProviders.of((FragmentActivity) activity).get(StateFragmentViewModel.class);
    }


}
