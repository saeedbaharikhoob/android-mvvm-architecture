package com.saeedbaharikhoob.testproject.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.FragmentUserBinding;
import com.saeedbaharikhoob.testproject.utils.Account;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.utils.IntentKeys;
import com.saeedbaharikhoob.testproject.utils.RxBus;
import com.saeedbaharikhoob.testproject.view.LoginActivity;
import com.saeedbaharikhoob.testproject.view.RegisterActivity;
import com.saeedbaharikhoob.testproject.view.StatePostsResultActivity;
import com.saeedbaharikhoob.testproject.view.adapter.StateAdapter;
import com.saeedbaharikhoob.testproject.view.di.componet.DaggerUserFragmentComponent;
import com.saeedbaharikhoob.testproject.view.di.module.UserFragmentModule;
import com.saeedbaharikhoob.testproject.view.interfaces.UserFragmentNavigator;
import com.saeedbaharikhoob.testproject.viewmodel.StateFragmentViewModel;
import com.saeedbaharikhoob.testproject.viewmodel.UserFragmentViewModel;

import javax.inject.Inject;

import dagger.android.DaggerFragment;


public class UserFragment extends Fragment implements UserFragmentNavigator {


    @Inject
    FragmentUserBinding fragmentUserBinding;
    @Inject
    UserFragmentViewModel userFragmentViewModel;


    private Activity mActivity;
    private LinearLayout layoutLogout;
    private LinearLayout layoutLogin;


    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();


        return fragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        DaggerUserFragmentComponent.builder().userFragmentModule(new UserFragmentModule(mActivity, inflater, container)).build().inject(this);


        return fragmentUserBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);

        layoutLogin = fragmentUserBinding.layoutLogin;
        layoutLogout = fragmentUserBinding.layoutLogout;

        userFragmentViewModel.setNavigator(this);
        fragmentUserBinding.setFragmentItem(userFragmentViewModel);


        RxBus.subscribe(getString(R.string.RefreshLoginStatus), this, (message) -> {
            LoginStatus();
        });

        LoginStatus();
    }


    @Override
    public void LoginStatus() {
        if (Account.getInstant(mActivity).getStatusLogin()) {
            layoutLogin.setVisibility(View.GONE);
            layoutLogout.setVisibility(View.VISIBLE);
        } else {
            layoutLogin.setVisibility(View.VISIBLE);
            layoutLogout.setVisibility(View.GONE);
        }
    }
}
