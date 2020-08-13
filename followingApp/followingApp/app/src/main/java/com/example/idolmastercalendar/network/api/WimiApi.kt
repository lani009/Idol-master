package com.example.idolmastercalendar.network.api

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WimiApi {

    @GET("login")
    fun login(@Query("id") id: String): Call<ResponseBody>

    @GET("review/set")
    fun saveReview(@Query("id") id: String, @Query("content") content: String, @Query("place") place: String, @Query("summery") summery: String): Call<ResponseBody>

    @GET("review/get")
    fun getReview(@Query("place") place: String): Call<JsonObject>
}