package com.saeedbaharikhoob.testproject.view.di.componet;


import com.saeedbaharikhoob.testproject.view.NewsActivity;
import com.saeedbaharikhoob.testproject.view.RegisterActivity;
import com.saeedbaharikhoob.testproject.view.di.module.NewsModule;
import com.saeedbaharikhoob.testproject.view.di.module.RegisterModule;

import dagger.Component;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */
@Component(modules = {RegisterModule.class})
public interface RegisterComponent {

    void inject(RegisterActivity registerActivity);

}
