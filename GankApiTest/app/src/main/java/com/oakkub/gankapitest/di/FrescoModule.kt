package com.oakkub.gankapitest.di

import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.oakkub.gankapitest.MainApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by OaKKuB on 8/21/2016.
 */
@Module
class FrescoModule(private val mainApplication: MainApplication) {

    @Singleton
    @Provides
    fun provideOkHttpPipelineConfig(okHttpClient: OkHttpClient) = OkHttpImagePipelineConfigFactory
            .newBuilder(mainApplication, okHttpClient)
            .build()

}