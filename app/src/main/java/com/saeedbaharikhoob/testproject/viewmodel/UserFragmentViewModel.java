package com.saeedbaharikhoob.testproject.viewmodel;

import android.content.Intent;
import android.text.TextUtils;

import androidx.lifecycle.ViewModel;

import com.saeedbaharikhoob.testproject.utils.Account;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.view.LoginActivity;
import com.saeedbaharikhoob.testproject.view.RegisterActivity;
import com.saeedbaharikhoob.testproject.view.interfaces.LoginNavigator;
import com.saeedbaharikhoob.testproject.view.interfaces.UserFragmentNavigator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class UserFragmentViewModel extends BaseViewModel<UserFragmentNavigator> {



    public void onLoginClick() {
        Intent intent = G.tools.getIntent();
        intent.setClass(G.context, LoginActivity.class);
        G.context.startActivity(intent);
    }
    public void onRegisterClick() {
        Intent intent = G.tools.getIntent();
        intent.setClass(G.context, RegisterActivity.class);
        G.context.startActivity(intent);
    }
    public void onLogoutClick() {
        Account.getInstant(G.context).storeUserId(0);
        Account.getInstant(G.context).storeStatusLogin(false);
        getNavigator().LoginStatus();
    }
}
