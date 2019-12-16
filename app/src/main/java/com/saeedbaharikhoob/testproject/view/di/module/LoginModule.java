package com.saeedbaharikhoob.testproject.view.di.module;

import android.app.Activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ActivityLoginBinding;
import com.saeedbaharikhoob.testproject.databinding.ActivityRegisterBinding;
import com.saeedbaharikhoob.testproject.viewmodel.LoginViewModel;
import com.saeedbaharikhoob.testproject.viewmodel.RegisterViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */

@Module
public class LoginModule {
    Activity activity;

    public LoginModule(Activity activity) {
        this.activity = activity;


    }
    @Provides
    ActivityLoginBinding getActivityNewsBinding() {
        return DataBindingUtil.setContentView(activity, R.layout.activity_login);
    }


    @Provides
    LoginViewModel getNewsViewModel() {
        return ViewModelProviders.of((FragmentActivity) activity).get(LoginViewModel.class);
    }



}
