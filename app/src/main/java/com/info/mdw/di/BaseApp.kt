package com.info.mdw.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        //appComponent = DaggerAppComponent.builder().appModule(AppModule).build()
    }
}