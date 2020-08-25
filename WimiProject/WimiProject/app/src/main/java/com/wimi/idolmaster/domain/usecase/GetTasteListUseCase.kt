package com.wimi.idolmaster.domain.usecase

import com.wimi.idolmaster.domain.repository.WimiRepository

class GetTasteListUseCase(private val wimiRepository: WimiRepository) {
    operator fun invoke() = wimiRepository.getTasteList()
}