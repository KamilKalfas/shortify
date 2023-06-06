package com.kkalfas.shortly.history.domain

import javax.inject.Inject

class GetLinkHistoryUseCase @Inject constructor(
    private val repository: HistoryRepository
) {
    operator fun invoke() = repository.getLinkHistoryStream()
}
