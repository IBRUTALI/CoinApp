package com.ighorosipov.coinapp.feature.cryptocurrency.data.dto.model.detail


import com.google.gson.annotations.SerializedName

data class ReposUrl(
    @SerializedName("bitbucket")
    val bitbucket: List<Any>,
    @SerializedName("github")
    val github: List<String>
)