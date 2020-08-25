package com.wimi.idolmaster.data.datasource

import com.wimi.idolmaster.data.api.GitApi
import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.model.GistsPublic

class GitRemoteDataSource(private val gitApi: GitApi): GitDataSource {

    override suspend fun getGistsPublic(): Result<List<GistsPublic>> {
        val response = gitApi.getGistsPublic().execute()

        return if(response.isSuccessful) {
            Result.Success(response.body() ?: ArrayList())
        } else {
            Result.Error(Exception("Network Error"))
        }
    }

}