package com.ighorosipov.coinapp.feature.cryptocurrency.data.dto.model


import com.google.gson.annotations.SerializedName

data class RoiDto(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("percentage")
    val percentage: Double,
    @SerializedName("times")
    val times: Double
)