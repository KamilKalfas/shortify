package com.kkalfas.shortly.domain.history.usecase

import com.kkalfas.shortly.data.history.model.entities.LinkEntity
import com.kkalfas.shortly.data.history.repository.HistoryRepository
import javax.inject.Inject

class ShortenUrlUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(url: String) : LinkEntity {
        return historyRepository.shortenUrl(url)
    }
}
