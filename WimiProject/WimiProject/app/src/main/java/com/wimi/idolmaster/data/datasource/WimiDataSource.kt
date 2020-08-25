package com.wimi.idolmaster.data.datasource

import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.model.GistsPublic
import com.wimi.idolmaster.domain.model.Review
import okhttp3.ResponseBody
import org.json.JSONObject

interface WimiDataSource {
    suspend fun login(id: String): Result<JSONObject>
    suspend fun writeReview(id: String, content: String, place: String, summery: String): Result<JSONObject>
    suspend fun getReview(place: String): Result<List<Review>>
    suspend fun getTasteList(): Result<List<String>>
}