package com.ighorosipov.coinapp.feature.cryptocurrency.domain.model

data class Cryptocurrency(
    val id: String,
    val name: String,
    val image: String,
    val symbol: String,
    val currentPrice: String,
    val currencySymbol: String = "$",
    val priceChangePercentage24h: String,
)


