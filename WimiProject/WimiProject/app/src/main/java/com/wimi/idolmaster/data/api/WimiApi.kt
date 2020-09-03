package com.wimi.idolmaster.data.api

import com.google.gson.JsonObject
import com.wimi.idolmaster.domain.model.Review
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WimiApi {

    @GET("login")
    fun login(@Query("id") id: String): Call<ResponseBody>

    @GET("review/set")
    fun writeReview(@Query("id") id: String, @Query("content") content: String, @Query("place") place: String, @Query("summery") summery: String): Call<ResponseBody>

    @GET("review/get")
    fun getReview(@Query("place") place: String): Call<JsonObject>

    @GET("review/getmy")
    fun getMyReview(@Query("id") id: String): Call<JsonObject>

    @GET("taste/show")
    fun getTasteList(): Call<JsonObject>

}