package com.saeedbaharikhoob.testproject.view.di.componet;


import com.saeedbaharikhoob.testproject.view.LoginActivity;
import com.saeedbaharikhoob.testproject.view.RegisterActivity;
import com.saeedbaharikhoob.testproject.view.di.module.LoginModule;
import com.saeedbaharikhoob.testproject.view.di.module.RegisterModule;

import dagger.Component;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */
@Component(modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

}
