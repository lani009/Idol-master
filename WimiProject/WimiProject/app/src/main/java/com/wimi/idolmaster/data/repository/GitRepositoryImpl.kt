package com.wimi.idolmaster.data.repository

import com.wimi.idolmaster.data.datasource.GitDataSource
import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.repository.GitRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class GitRepositoryImpl(private val gitDataSource: GitDataSource): GitRepository {
    override fun getGistsPublic() = flow {
        emit(Result.Loading)
        delay(1000)
        emit(gitDataSource.getGistsPublic())
    }
}