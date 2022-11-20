package com.kkalfas.shortly.domain.history.usecase

import com.kkalfas.shortly.data.history.repository.HistoryRepository
import javax.inject.Inject

class DeleteLinkUseCase @Inject constructor(
    private val repository: HistoryRepository
) {
    suspend operator fun invoke(code: String) = repository.deleteLink(code)
}
