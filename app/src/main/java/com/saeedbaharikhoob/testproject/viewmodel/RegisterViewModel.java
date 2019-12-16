package com.saeedbaharikhoob.testproject.viewmodel;

import android.text.TextUtils;

import com.saeedbaharikhoob.testproject.view.interfaces.RegisterNavigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RegisterViewModel extends BaseViewModel<RegisterNavigator> {


    public boolean isUsernameAndPasswordAndNickNameValid(String username, String password,String nickName) {

        if (TextUtils.isEmpty(username)) {
            return false;
        }
        if (TextUtils.isEmpty(nickName)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public void register(String nickname ,String username , String password) {

        getCompositeDisposable().add(getWebservice().register(nickname,username,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> getNavigator().result(response), throwable -> getNavigator().handleError(throwable)));
    }

    public void onServerRegisterClick() {
        getNavigator().register();
    }

}
