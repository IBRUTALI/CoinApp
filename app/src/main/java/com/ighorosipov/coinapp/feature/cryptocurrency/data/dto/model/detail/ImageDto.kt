package com.ighorosipov.coinapp.feature.cryptocurrency.data.dto.model.detail


import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("large")
    val large: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("thumb")
    val thumb: String
)