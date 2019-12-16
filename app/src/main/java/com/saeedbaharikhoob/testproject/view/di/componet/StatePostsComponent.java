package com.saeedbaharikhoob.testproject.view.di.componet;


import com.saeedbaharikhoob.testproject.view.StatePostsResultActivity;
import com.saeedbaharikhoob.testproject.view.di.module.StatePostsModule;

import dagger.Component;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */
@Component(modules = {StatePostsModule.class})
public interface StatePostsComponent {

    void inject(StatePostsResultActivity postsActivity);

}
