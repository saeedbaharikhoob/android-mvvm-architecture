package com.saeedbaharikhoob.testproject.view.di.componet;


import com.saeedbaharikhoob.testproject.view.di.module.TagsResultModule;
import com.saeedbaharikhoob.testproject.view.TagsResultActivity;

import dagger.Component;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */
@Component(modules = {TagsResultModule.class})
public interface TagsResultComponent {

    void inject(TagsResultActivity tagsResultActivity);

}
