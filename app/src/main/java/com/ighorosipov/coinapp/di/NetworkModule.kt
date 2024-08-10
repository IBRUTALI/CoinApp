package com.ighorosipov.coinapp.di

import com.ighorosipov.coinapp.feature.cryptocurrency.data.dto.CoinGeckoApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.ighorosipov.coinapp.BuildConfig
import retrofit2.create

@Module
interface NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun provideCurrencyService(okHttpClient: OkHttpClient): CoinGeckoApi {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }

        @Provides
        @Singleton
        fun provideOkhttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
            return client.build()
        }

    }

}