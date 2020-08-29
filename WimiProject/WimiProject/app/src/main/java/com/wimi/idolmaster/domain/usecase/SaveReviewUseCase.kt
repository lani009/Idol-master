package com.wimi.idolmaster.domain.usecase

import com.wimi.idolmaster.domain.repository.WimiRepository

class SaveReviewUseCase(private val wimiRepository: WimiRepository) {
    operator fun invoke(id: String, content: String, place: String, summery: String) = wimiRepository.saveReview(id, content, place, summery)
}