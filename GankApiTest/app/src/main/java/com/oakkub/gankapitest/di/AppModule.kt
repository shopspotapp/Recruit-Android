package com.oakkub.gankapitest.di

import com.oakkub.gankapitest.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by OaKKuB on 8/17/2016.
 */
@Module
class AppModule(private val mainApplication: MainApplication) {

    @Singleton
    @Provides
    fun provideMainApplication() = mainApplication

}
