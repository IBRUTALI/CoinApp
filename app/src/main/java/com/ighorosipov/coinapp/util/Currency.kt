package com.ighorosipov.coinapp.util

enum class Currency(val vsCurrency: String, val symbol: String = "$") {
    USD("USD", "$"),
    RUB("RUB", "₽")
}