package com.saeedbaharikhoob.testproject.view.di.module;

import android.app.Activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ActivityLoginBinding;
import com.saeedbaharikhoob.testproject.databinding.ActivityMainBinding;
import com.saeedbaharikhoob.testproject.view.adapter.MainPagePagerAdapter;
import com.saeedbaharikhoob.testproject.viewmodel.LoginViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */

@Module
public class MainModule {
    Activity activity;
    FragmentManager fragmentManager;
    public MainModule(Activity activity, FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;

    }


    @Provides
    MainPagePagerAdapter getMainPagePagerAdapter() {
        return new MainPagePagerAdapter(fragmentManager);
    }

    @Provides
    ActivityMainBinding getActivityNewsBinding() {
        return DataBindingUtil.setContentView(activity, R.layout.activity_main);
    }






}
