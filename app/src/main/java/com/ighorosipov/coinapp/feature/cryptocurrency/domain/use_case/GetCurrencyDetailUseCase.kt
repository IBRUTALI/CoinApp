package com.ighorosipov.coinapp.feature.cryptocurrency.domain.use_case

import com.ighorosipov.coinapp.feature.cryptocurrency.domain.repository.CryptocurrencyRepository
import com.ighorosipov.coinapp.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrencyDetailUseCase @Inject constructor(
    private val repository: CryptocurrencyRepository
) {

    suspend operator fun invoke(coinId: String) = flow {
        emit(Resource.Loading())
        emit(repository.getCryptocurrencyDetail(coinId = coinId))
    }

}