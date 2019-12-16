package com.saeedbaharikhoob.testproject.view.di.componet;


import com.saeedbaharikhoob.testproject.view.di.module.NewsFragmentModule;
import com.saeedbaharikhoob.testproject.view.di.module.SearchFragmentModule;
import com.saeedbaharikhoob.testproject.view.fragments.NewsFragment;
import com.saeedbaharikhoob.testproject.view.fragments.SearchFragment;

import dagger.Component;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */
@Component(modules = {SearchFragmentModule.class})
public interface SearchFragmentComponent {

    void inject(SearchFragment searchFragment);

}
