package com.wimi.idolmaster.domain.usecase

import com.wimi.idolmaster.domain.repository.GitRepository

class GetGistsPublicUseCase(private val gitRepository: GitRepository) {
    operator fun invoke() = gitRepository.getGistsPublic()
}