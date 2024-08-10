package com.ighorosipov.coinapp.feature.cryptocurrency.domain.model

import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.detail.Description
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.detail.Image

data class CryptocurrencyDetail(
    val id: String,
    val image: Image,
    val description: Description,
    val categories: List<String>
)