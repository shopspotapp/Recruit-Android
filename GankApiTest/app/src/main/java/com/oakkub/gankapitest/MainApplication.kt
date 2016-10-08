package com.oakkub.gankapitest

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.oakkub.gankapitest.di.*

/**
 * Created by OaKKuB on 10/3/2016.
 */
class MainApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .frescoModule(FrescoModule(this))
                .webServiceModule(WebServiceModule())
                .build()

        Fresco.initialize(this, appComponent.okhttpImagePipelineConfig())
    }

}