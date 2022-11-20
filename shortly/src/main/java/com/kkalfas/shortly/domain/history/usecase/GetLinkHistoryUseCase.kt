package com.kkalfas.shortly.domain.history.usecase

import com.kkalfas.shortly.data.history.repository.HistoryRepository
import javax.inject.Inject

class GetLinkHistoryUseCase @Inject constructor(
    private val repository: HistoryRepository
) {
    operator fun invoke() = repository.getLinkHistoryStream()
}
