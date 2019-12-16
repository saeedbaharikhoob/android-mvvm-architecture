package com.saeedbaharikhoob.testproject.view.di.componet;


import com.saeedbaharikhoob.testproject.view.MainActivity;
import com.saeedbaharikhoob.testproject.view.di.module.MainModule;

import dagger.Component;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */
@Component(modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity activity);

}
