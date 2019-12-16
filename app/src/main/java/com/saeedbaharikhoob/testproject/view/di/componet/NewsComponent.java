package com.saeedbaharikhoob.testproject.view.di.componet;


import com.saeedbaharikhoob.testproject.view.MainActivity;
import com.saeedbaharikhoob.testproject.view.NewsActivity;
import com.saeedbaharikhoob.testproject.view.di.module.MainModule;
import com.saeedbaharikhoob.testproject.view.di.module.NewsModule;

import dagger.Component;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */
@Component(modules = {NewsModule.class})
public interface NewsComponent {

    void inject(NewsActivity activity);

}
