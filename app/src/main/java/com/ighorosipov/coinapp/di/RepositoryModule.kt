package com.ighorosipov.coinapp.di

import com.ighorosipov.coinapp.feature.cryptocurrency.data.repository.CryptocurrencyRepositoryImpl
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.repository.CryptocurrencyRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindCryptocurrencyRepository(
        cryptocurrencyRepositoryImpl: CryptocurrencyRepositoryImpl,
    ): CryptocurrencyRepository

}