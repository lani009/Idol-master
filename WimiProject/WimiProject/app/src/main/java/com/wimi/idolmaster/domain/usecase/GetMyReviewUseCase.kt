package com.wimi.idolmaster.domain.usecase

import com.wimi.idolmaster.domain.repository.WimiRepository

class GetMyReviewUseCase(private val wimiRepository: WimiRepository) {
    operator fun invoke(id: String) = wimiRepository.getMyReview(id)
}