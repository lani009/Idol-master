package com.wimi.idolmaster.domain.repository

import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.model.Review
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject

interface WimiRepository {
    fun login(id: String): Flow<Result<JSONObject>>
    fun writeReview(id: String, content: String, place: String, summery: String): Flow<Result<JSONObject>>
    fun getReview(place: String): Flow<Result<List<Review>>>
    fun getTasteList(): Flow<Result<List<String>>>
}