package com.ighorosipov.coinapp.di

import android.app.Application
import com.ighorosipov.coinapp.MainActivity
import com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coin_detail.CoinDetailFragment
import com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coin_detail.CoinDetailViewModel
import com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins.CoinsFragment
import com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins.CoinsViewModel
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
    fun inject(coinsFragment: CoinsFragment)
    fun inject(coinDetailFragment: CoinDetailFragment)

    fun coinsViewModel(): CoinsViewModel.Factory
    fun coinDetailViewModel(): CoinDetailViewModel.Factory
}