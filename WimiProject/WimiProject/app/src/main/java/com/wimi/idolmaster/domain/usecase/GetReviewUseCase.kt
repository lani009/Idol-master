package com.wimi.idolmaster.domain.usecase

import com.wimi.idolmaster.domain.repository.WimiRepository

class GetReviewUseCase(private val wimiRepository: WimiRepository) {
    operator fun invoke(place: String) = wimiRepository.getReview(place)
}