package com.wimi.idolmaster.data.api

import com.wimi.idolmaster.domain.model.GistsPublic
import retrofit2.Call
import retrofit2.http.POST

interface GitApi {

    @POST("gists/public")
    fun getGistsPublic(): Call<List<GistsPublic>>

}