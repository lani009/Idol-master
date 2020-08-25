package com.wimi.idolmaster.domain.model

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("writer") val user: String,
    @SerializedName("content") val content: String
)