package com.ighorosipov.coinapp

import android.app.Application
import com.ighorosipov.coinapp.di.AppComponent
import com.ighorosipov.coinapp.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }


}