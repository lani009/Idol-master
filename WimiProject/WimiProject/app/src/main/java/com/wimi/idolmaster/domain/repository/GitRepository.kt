package com.wimi.idolmaster.domain.repository

import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.model.GistsPublic
import kotlinx.coroutines.flow.Flow

interface GitRepository {
    fun getGistsPublic(): Flow<Result<List<GistsPublic>>>
}