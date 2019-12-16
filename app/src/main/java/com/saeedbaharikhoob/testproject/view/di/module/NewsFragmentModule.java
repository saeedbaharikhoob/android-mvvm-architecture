package com.saeedbaharikhoob.testproject.view.di.module;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.FragmentNewsBinding;
import com.saeedbaharikhoob.testproject.databinding.FragmentSearchBinding;
import com.saeedbaharikhoob.testproject.view.adapter.NewsAdapter;
import com.saeedbaharikhoob.testproject.viewmodel.NewsFragmentViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */

@Module
public class NewsFragmentModule {
    Activity activity;
    LayoutInflater inflater;
    ViewGroup container;
    public NewsFragmentModule(Activity activity, LayoutInflater inflater, ViewGroup container) {
        this.activity = activity;
        this.inflater = inflater;
        this.container = container;

    }
    @Provides
    FragmentNewsBinding getActivitysBinding() {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_news,container,false);
    }


    @Provides
    GridLayoutManager getGridLayoutManager() {
        return new GridLayoutManager(activity, 1);
    }

    @Provides
    NewsAdapter getPostAdapter() {
        return new NewsAdapter();
    }



    @Provides
    NewsFragmentViewModel getPostViewModel() {
        return ViewModelProviders.of((FragmentActivity) activity).get(NewsFragmentViewModel.class);
    }


}
