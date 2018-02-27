package com.example.inforesource.di;

import android.content.Context;

import com.example.inforesource.data.repository.AppRepository;
import com.example.inforesource.di.modules.ContextModule;
import com.example.inforesource.di.modules.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Libgo on 27.02.2018.
 */

@Singleton
@Component (modules = {RepositoryModule.class, ContextModule.class})
public interface AppComponent {

    Context getContext();

    AppRepository getAppRepository();

}
