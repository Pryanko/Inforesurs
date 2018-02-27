package com.example.inforesource.di.modules;

import com.example.inforesource.data.repository.AppRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Libgo on 27.02.2018.
 *
 */
@Module
@Singleton
public class RepositoryModule {

    @Provides
    @Singleton
    AppRepository appRepository(){
        return new AppRepository();
    }
}
