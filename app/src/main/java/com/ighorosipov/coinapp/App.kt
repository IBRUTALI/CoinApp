package com.ighorosipov.coinapp

import android.app.Application
import com.ighorosipov.coinapp.di.AppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }


}