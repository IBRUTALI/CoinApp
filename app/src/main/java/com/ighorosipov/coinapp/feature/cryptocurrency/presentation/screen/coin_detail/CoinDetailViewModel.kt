package com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coin_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ighorosipov.coinapp.di.IODispatcher
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.CryptocurrencyDetail
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.use_case.GetCryptocurrencyDetailUseCase
import com.ighorosipov.coinapp.util.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoinDetailViewModel @AssistedInject constructor(
    private val getCryptocurrencyDetailUseCase: GetCryptocurrencyDetailUseCase,
    @Assisted private val coinId: String?,
    @IODispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    private val _cryptocurrency = MutableStateFlow<CryptocurrencyDetail?>(null)
    val cryptocurrency: StateFlow<CryptocurrencyDetail?> = _cryptocurrency

    init {
        coinId?.let {
            getCryptocurrencyDetail(it)
        }
    }

    fun onEvent(event: CoinDetailEvent) {
        when(event) {
            is CoinDetailEvent.GetCurrencyDetail -> {
                coinId?.let {
                    getCryptocurrencyDetail(it)
                }
            }
        }
    }

    private fun getCryptocurrencyDetail(coinId: String) {
        viewModelScope.launch(dispatcher) {
            getCryptocurrencyDetailUseCase(coinId).collect { resource ->
                when(resource) {
                    is Resource.Error -> {
                        _isLoading.emit(false)
                        _isError.emit(true)
                    }
                    is Resource.Loading -> {
                        _isError.emit(false)
                        _isLoading.emit(true)
                    }
                    is Resource.Success -> {
                        _isError.emit(false)
                        _isLoading.emit(false)
                        _cryptocurrency.emit(resource.data)
                    }
                }

            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(coinId: String?): CoinDetailViewModel
    }

}