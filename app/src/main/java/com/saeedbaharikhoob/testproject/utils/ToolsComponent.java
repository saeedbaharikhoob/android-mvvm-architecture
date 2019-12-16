package com.saeedbaharikhoob.testproject.utils;

import android.content.Intent;
import android.os.Handler;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */
@Component(modules = {ToolsModule.class})
@Singleton
public interface ToolsComponent {

    Retrofit getRetrofit();

    Intent getIntent();

    Handler getHandler();

    ItemImage getItemImage();

    ItemText getItemText();


}
