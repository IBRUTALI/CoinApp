package com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coin_detail

import com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins.CoinsScreenEvent

sealed interface CoinDetailEvent {
    data object GetCurrencyDetail : CoinDetailEvent
}