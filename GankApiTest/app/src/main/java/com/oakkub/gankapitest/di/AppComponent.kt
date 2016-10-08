package com.oakkub.gankapitest.di

import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.oakkub.gankapitest.MainApplication
import com.oakkub.gankapitest.data.gank.GankApiService
import com.oakkub.gankapitest.ui.detail.DetailFragment
import com.oakkub.gankapitest.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by OaKKuB on 8/17/2016.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        FrescoModule::class,
        WebServiceModule::class
))
interface AppComponent {

    // AppModule
    fun mainApplication(): MainApplication

    // WebServiceModule
    fun okhttpImagePipelineConfig(): ImagePipelineConfig
    fun unsplashApiService(): GankApiService

    // Activity/Fragment injection
    fun inject(mainFragment: MainFragment)
    fun inject(detailFragment: DetailFragment)

}