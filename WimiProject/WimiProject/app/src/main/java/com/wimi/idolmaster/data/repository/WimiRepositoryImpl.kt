package com.wimi.idolmaster.data.repository

import com.wimi.idolmaster.data.datasource.WimiDataSource
import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.model.Review
import com.wimi.idolmaster.domain.repository.WimiRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody

class WimiRepositoryImpl(private val wimiDataSource: WimiDataSource): WimiRepository {

    override fun login(id: String) = flow {
        emit(Result.Loading)
        emit(wimiDataSource.login(id))
    }

    override fun writeReview(
        id: String,
        content: String,
        place: String,
        summery: String
    ) = flow {
        emit(Result.Loading)
        emit(wimiDataSource.writeReview(id, content, place, summery))
    }

    override fun getReview(place: String) = flow {
        emit(Result.Loading)
        emit(wimiDataSource.getReview(place))
    }

    override fun getMyReview(id: String) = flow {
        emit(Result.Loading)
        emit(wimiDataSource.getMyReview(id))
    }

    override fun getTasteList() = flow {
        emit(Result.Loading)
        emit(wimiDataSource.getTasteList())
    }

    override fun saveReview(id: String, content: String, place: String, summery: String) = flow {
        emit(Result.Loading)
        emit(wimiDataSource.saveReview(id, content, place, summery))
    }
}