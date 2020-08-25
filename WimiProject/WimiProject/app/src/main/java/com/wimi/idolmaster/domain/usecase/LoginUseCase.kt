package com.wimi.idolmaster.domain.usecase

import com.wimi.idolmaster.domain.repository.WimiRepository

class LoginUseCase(private val wimiRepository: WimiRepository) {
    operator fun invoke(id: String) = wimiRepository.login(id)
}