package com.saeedbaharikhoob.testproject.view.di.module;

import android.app.Activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ActivityStatePostsBinding;
import com.saeedbaharikhoob.testproject.databinding.ActivityTagsResultBinding;
import com.saeedbaharikhoob.testproject.view.adapter.SearchAdapter;
import com.saeedbaharikhoob.testproject.viewmodel.StatePostsViewModel;
import com.saeedbaharikhoob.testproject.viewmodel.TagsResultViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */

@Module
public class TagsResultModule {
    Activity activity;

    public TagsResultModule(Activity activity) {
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
    TagsResultViewModel getNewsViewModel() {
        return ViewModelProviders.of((FragmentActivity) activity).get(TagsResultViewModel.class);
    }

    @Provides
    ActivityTagsResultBinding getActivityNewsBinding() {
        return DataBindingUtil.setContentView(activity, R.layout.activity_tags_result);
    }



}
