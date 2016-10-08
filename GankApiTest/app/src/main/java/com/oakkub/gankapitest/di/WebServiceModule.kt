package com.oakkub.gankapitest.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.oakkub.gankapitest.BuildConfig
import com.oakkub.gankapitest.data.WebServiceConfig
import com.oakkub.gankapitest.data.gank.GankApiService
import com.oakkub.gankapitest.data.gank.converteradapter.DateAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by OaKKuB on 8/17/2016.
 */
@Module
class WebServiceModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

        val logger = HttpLoggingInterceptor()
        logger.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addNetworkInterceptor(logger)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi) = Retrofit.Builder()
            .baseUrl(WebServiceConfig.GANK_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideUnsplashApi(retrofit: Retrofit) = retrofit.create(GankApiService::class.java)

    @Singleton
    @Provides
    fun provideMoshiConverter(): Moshi = Moshi.Builder()
            .add(Date::class.java, DateAdapter(SimpleDateFormat(WebServiceConfig.Companion.GANK_BASE_DATE_FORMAT, Locale.getDefault())))
            .build()

}