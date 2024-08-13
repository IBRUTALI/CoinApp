package com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins

import com.ighorosipov.coinapp.util.Currency

sealed interface CoinsScreenEvent {

    data object GetCurrencies : CoinsScreenEvent
    data class ChangeCurrency(val currency: Currency) : CoinsScreenEvent
    data object PullToRefresh : CoinsScreenEvent
}