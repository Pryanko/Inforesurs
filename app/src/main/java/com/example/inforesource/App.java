package com.example.inforesource;

import android.app.Application;

import com.example.inforesource.di.AppComponent;
import com.example.inforesource.di.DaggerAppComponent;
import com.example.inforesource.di.modules.ContextModule;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author Libgo on 26.02.2018.
 */

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
