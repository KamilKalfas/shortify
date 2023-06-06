package com.kkalfas.shortly.history.domain

import javax.inject.Inject

class ShortenUrlUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(url: String)  {
        historyRepository.shortenUrl(url)
    }
}
