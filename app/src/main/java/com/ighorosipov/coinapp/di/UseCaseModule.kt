package com.ighorosipov.coinapp.di

import com.ighorosipov.coinapp.feature.cryptocurrency.domain.repository.CryptocurrencyRepository
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.use_case.GetCryptocurrenciesUseCase
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.use_case.GetCryptocurrencyDetailUseCase
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
        ): GetCryptocurrenciesUseCase = GetCryptocurrenciesUseCase(repository)

        @Provides
        @Singleton
        fun provideGetCryptocurrencyDetailUseCase(
            repository: CryptocurrencyRepository
        ): GetCryptocurrencyDetailUseCase = GetCryptocurrencyDetailUseCase(repository)

    }
}