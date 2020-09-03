package com.wimi.idolmaster.domain.model

import com.google.gson.annotations.SerializedName

data class MyReview(
    @SerializedName("place") val place: String,
    @SerializedName("content") val content: String,
    @SerializedName("summery") val summery: String
)