package com.example.idolmastercalendar.data

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("writer") val user: String,
    @SerializedName("content") val content: String
)