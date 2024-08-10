package com.ighorosipov.coinapp.feature.cryptocurrency.data.dto

import com.ighorosipov.coinapp.feature.cryptocurrency.data.dto.model.CryptocurrencyDetailDto
import com.ighorosipov.coinapp.feature.cryptocurrency.data.dto.model.CryptocurrencyDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {

    @GET("$COINS/$MARKETS")
    suspend fun getCryptocurrencies(
        @Query("vs_currency") vsCurrency: String,
        @Query("per_page") perPage: String? = null
    ): List<CryptocurrencyDto>

    @GET("$COINS/{id}")
    suspend fun getCryptocurrencyDetail(
        @Path("id") coinId: String
    ): CryptocurrencyDetailDto

    companion object {
        const val COINS = "coins"
        const val MARKETS = "markets"
    }

}