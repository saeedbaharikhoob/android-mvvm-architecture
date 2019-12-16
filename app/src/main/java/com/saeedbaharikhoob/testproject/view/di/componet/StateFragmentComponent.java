package com.saeedbaharikhoob.testproject.view.di.componet;


import com.saeedbaharikhoob.testproject.view.di.module.StateFragmentModule;
import com.saeedbaharikhoob.testproject.view.fragments.StateFragment;

import dagger.Component;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */
@Component(modules = {StateFragmentModule.class})
public interface StateFragmentComponent {

    void inject(StateFragment stateFragment);

}
