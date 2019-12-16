package com.saeedbaharikhoob.testproject.viewmodel;

import android.text.TextUtils;
import com.saeedbaharikhoob.testproject.view.interfaces.LoginNavigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LoginViewModel extends BaseViewModel<LoginNavigator> {


    public boolean isUsernameAndPasswordValid(String username, String password) {

        if (TextUtils.isEmpty(username)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public void login(String username, String password) {

        getCompositeDisposable().add(getWebservice().login(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> getNavigator().result(response), throwable -> getNavigator().handleError(throwable)));
    }

    public void onServerLoginClick() {
        getNavigator().login();
    }


}
