package com.ighorosipov.coinapp.feature.cryptocurrency.domain.model

data class Cryptocurrency(
    val id: String,
    val image: String,
    val symbol: String,
    val currentPrice: Double,
    val currencySymbol: String = "$",
    val priceChangePercentage24h: Double,
)


