package com.ighorosipov.coinapp.di

import com.ighorosipov.coinapp.feature.cryptocurrency.domain.repository.CryptocurrencyRepository
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.use_case.GetCurrenciesUseCase
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.use_case.GetCurrencyDetailUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface UseCaseModule {

    companion object {
        @Provides
        @Singleton
        fun provideGetCryptocurrenciesUseCase(
            repository: CryptocurrencyRepository
        ): GetCurrenciesUseCase = GetCurrenciesUseCase(repository)
        @Provides
        @Singleton
        fun provideGetCryptocurrencyDetailUseCase(
            repository: CryptocurrencyRepository
        ): GetCurrencyDetailUseCase = GetCurrencyDetailUseCase(repository)
    }
}