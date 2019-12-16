package com.saeedbaharikhoob.testproject.view.interfaces;


import com.saeedbaharikhoob.testproject.model.retromodel.RegisterRetro;

public interface RegisterNavigator {

    void handleError(Throwable throwable);
    void register();
    void result(RegisterRetro registerRetro);

}
