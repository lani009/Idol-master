package com.wimi.idolmaster.data.datasource

import com.google.gson.JsonArray
import com.wimi.idolmaster.data.api.WimiApi
import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.model.Review
import com.wimi.idolmaster.utils.AppLogger
import okhttp3.ResponseBody
import org.json.JSONObject

class WimiRemoteDataSource(private val wimiApi: WimiApi): WimiDataSource {

    override suspend fun login(id: String): Result<JSONObject> {
        val response = wimiApi.login(id).execute()

        return if(response.isSuccessful) {
            Result.Success(JSONObject().apply { put("result", "ok") })
        } else {
            Result.Error(Exception("Network Error"))
        }
    }

    override suspend fun writeReview(
        id: String,
        content: String,
        place: String,
        summery: String
    ): Result<JSONObject> {
        val response = wimiApi.writeReview(id, content, place, summery).execute()

        return if(response.isSuccessful) {
            Result.Success(JSONObject().apply { put("result", "ok") })
        } else {
            Result.Error(Exception("Network Error"))
        }
    }

    override suspend fun getReview(place: String): Result<List<Review>> {
        val response = wimiApi.getReview(place).execute()

        return if(response.isSuccessful) {
            val reviewList = ArrayList<Review>()
            val reviewArray: JsonArray = response.body()!!.getAsJsonArray("review")

            for (i in 0 until reviewArray.size()) {
                reviewList.add(
                    Review(
                        reviewArray[i].asJsonObject["writer"].asString,
                        reviewArray[i].asJsonObject["content"].asString
                    )
                )
            }
            Result.Success(reviewList)
        } else {
            Result.Error(Exception("Network Error"))
        }
    }

    override suspend fun getTasteList(): Result<List<String>> {
        val response = wimiApi.getTasteList().execute()
        return if(response.isSuccessful) {
            AppLogger.e(response.body()!!.toString())
            val tasteObject = response.body()!!.getAsJsonArray("taste")
            val resultArray = ArrayList<String>()
            for(index in 0 until tasteObject.size()) {
                resultArray.add(tasteObject[index].toString().replace("\"", ""))
            }

            Result.Success( resultArray)
        } else {
            Result.Error(Exception("Network Error"))
        }
    }

    override suspend fun saveReview(
        id: String,
        content: String,
        place: String,
        summery: String
    ): Result<ResponseBody> {
        val response = wimiApi.writeReview(id, content, place, summery).execute()
        return if(response.isSuccessful) {
            Result.Success(response.body()!!)
        } else {
            Result.Error(Exception("Network Error"))
        }
    }

}