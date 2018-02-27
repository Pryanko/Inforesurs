package com.example.inforesource.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Libgo on 27.02.2018.
 */
@Module
@Singleton
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context context(){
        return context;
    }


}
