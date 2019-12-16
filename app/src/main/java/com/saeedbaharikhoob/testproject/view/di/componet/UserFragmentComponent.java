package com.saeedbaharikhoob.testproject.view.di.componet;


import com.saeedbaharikhoob.testproject.view.di.module.NewsFragmentModule;
import com.saeedbaharikhoob.testproject.view.di.module.UserFragmentModule;
import com.saeedbaharikhoob.testproject.view.fragments.NewsFragment;
import com.saeedbaharikhoob.testproject.view.fragments.UserFragment;

import dagger.Component;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */
@Component(modules = {UserFragmentModule.class})
public interface UserFragmentComponent {

    void inject(UserFragment userFragment);

}
