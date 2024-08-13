package com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ighorosipov.coinapp.di.IODispatcher
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.Cryptocurrency
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.use_case.GetCryptocurrenciesUseCase
import com.ighorosipov.coinapp.util.Currency
import com.ighorosipov.coinapp.util.Resource
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoinsViewModel @AssistedInject constructor(
    private val getCryptocurrenciesUseCase: GetCryptocurrenciesUseCase,
    @IODispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    private val _currentCurrency = MutableStateFlow(Currency.USD)
    val currentCurrency: StateFlow<Currency> = _currentCurrency

    private val _cryptocurrencies = MutableStateFlow(emptyList<Cryptocurrency>())
    val cryptocurrencies: StateFlow<List<Cryptocurrency>> = _cryptocurrencies

    init {
        getCryptocurrencies()
    }

    fun onEvent(event: CoinsScreenEvent) {
        when(event) {
            is CoinsScreenEvent.ChangeCurrency -> {
                _currentCurrency.value = event.currency
                getCryptocurrencies()
            }
            is CoinsScreenEvent.GetCurrencies -> {
                getCryptocurrencies()
            }
            is CoinsScreenEvent.PullToRefresh -> {
                //TODO
            }
        }
    }

    private fun getCryptocurrencies() {
        viewModelScope.launch(dispatcher) {
            getCryptocurrenciesUseCase(
                vsCurrency = currentCurrency.value.vsCurrency,
                perPage = "20"
            ).collect { resource ->
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
                        _cryptocurrencies.emit(resource.data ?: emptyList())
                    }
                }

            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(): CoinsViewModel
    }

}