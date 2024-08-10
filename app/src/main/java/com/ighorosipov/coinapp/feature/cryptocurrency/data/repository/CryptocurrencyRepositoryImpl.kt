package com.ighorosipov.coinapp.feature.cryptocurrency.data.repository

import com.ighorosipov.coinapp.feature.cryptocurrency.data.dto.CoinGeckoApi
import com.ighorosipov.coinapp.feature.cryptocurrency.data.mapper.CryptocurrencyMapper
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.Cryptocurrency
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.CryptocurrencyDetail
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.repository.CryptocurrencyRepository
import com.ighorosipov.coinapp.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptocurrencyRepositoryImpl @Inject constructor(
    private val api: CoinGeckoApi
): CryptocurrencyRepository {

    override suspend fun getCryptocurrency(
        vsCurrency: String,
        perPage: String?,
    ): Resource<List<Cryptocurrency>> {
        return try {
            val cryptocurrencies = api.getCryptocurrencies(vsCurrency, perPage).map { cryptocurrencyDto ->
                CryptocurrencyMapper().cryptocurrencyDtoToDomain(cryptocurrencyDto)
            }

            Resource.Success(
                data = cryptocurrencies
            )
        } catch (e: HttpException) {
            Resource.Error(message = "Ошибка сети")
        } catch (e: IOException) {
            Resource.Error(message = "Нет интернета")
        } catch (e: Exception) {
            Resource.Error(message = "Неизвестная ошибка")
        }
    }

    override suspend fun getCryptocurrencyDetail(coinId: String): Resource<CryptocurrencyDetail> {
        return try {

            val cryptocurrencyDetail = CryptocurrencyMapper().cryptocurrencyDetailDtoToDomain(
                api.getCryptocurrencyDetail(coinId = coinId)
            )

            Resource.Success(
                data = cryptocurrencyDetail
            )
        } catch (e: HttpException) {
            Resource.Error(message = "Ошибка сети")
        } catch (e: IOException) {
            Resource.Error(message = "Нет интернета")
        } catch (e: Exception) {
            Resource.Error(message = "Неизвестная ошибка")
        }
    }

}