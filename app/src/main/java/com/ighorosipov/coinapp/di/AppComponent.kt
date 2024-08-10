package com.ighorosipov.coinapp.di

import android.app.Application
import com.ighorosipov.coinapp.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DispatcherModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(mainActivity: MainActivity)

}