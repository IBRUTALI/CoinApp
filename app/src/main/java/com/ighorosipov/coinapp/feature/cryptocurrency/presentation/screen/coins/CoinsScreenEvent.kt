package com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins

sealed interface CoinsScreenEvent {

    data object GetCurrencies : CoinsScreenEvent
    data class ChangeCurrency(val currency: String) : CoinsScreenEvent
    data object PullToRefresh : CoinsScreenEvent
}