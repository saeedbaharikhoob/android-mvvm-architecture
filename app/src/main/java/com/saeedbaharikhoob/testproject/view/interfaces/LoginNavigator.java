package com.saeedbaharikhoob.testproject.view.interfaces;


import com.saeedbaharikhoob.testproject.model.retromodel.LoginRetro;

public interface LoginNavigator {

    void handleError(Throwable throwable);
    void login();
    void result(LoginRetro loginRetro);

}
