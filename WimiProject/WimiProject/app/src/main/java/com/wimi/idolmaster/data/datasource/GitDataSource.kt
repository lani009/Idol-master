package com.wimi.idolmaster.data.datasource

import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.model.GistsPublic

interface GitDataSource {
    suspend fun getGistsPublic(): Result<List<GistsPublic>>
}