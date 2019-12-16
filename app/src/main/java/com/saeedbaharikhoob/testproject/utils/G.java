package com.saeedbaharikhoob.testproject.utils;

import android.app.Application;
import android.content.Context;


/**
 * Created by SaeedBahariKhoob on 12/23/2017.
 */

public class G extends Application {

    public static Context context;
    public static ToolsComponent tools;
    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        tools = DaggerToolsComponent.builder()
                .toolsModule(new ToolsModule())
                .build();


    }

    public static Context getContext() {
        return context;
    }

   }
