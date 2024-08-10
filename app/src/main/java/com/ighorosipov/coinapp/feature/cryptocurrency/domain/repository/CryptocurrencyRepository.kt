package com.ighorosipov.coinapp.feature.cryptocurrency.domain.repository

import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.Cryptocurrency
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.CryptocurrencyDetail
import com.ighorosipov.coinapp.util.Resource

interface CryptocurrencyRepository {

    suspend fun getCryptocurrency(
        vsCurrency: String,
        perPage: String? = null
    ): Resource<List<Cryptocurrency>>

    suspend fun getCryptocurrencyDetail(coinId: String): Resource<CryptocurrencyDetail>

}