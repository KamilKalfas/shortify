package com.kkalfas.shortly.history.domain

import javax.inject.Inject

class DeleteLinkUseCase @Inject constructor(
    private val repository: HistoryRepository
) {
    suspend operator fun invoke(code: String) = repository.deleteLink(code)
}
