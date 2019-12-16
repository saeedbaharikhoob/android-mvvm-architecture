package com.saeedbaharikhoob.testproject.view.di.componet;


import com.saeedbaharikhoob.testproject.view.di.module.NewsFragmentModule;
import com.saeedbaharikhoob.testproject.view.fragments.NewsFragment;

import dagger.Component;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */
@Component(modules = {NewsFragmentModule.class})
public interface NewsFragmentComponent {

    void inject(NewsFragment newsFragment);

}
