package com.kkalfas.shortly.domain.history.usecase

import com.kkalfas.shortly.data.history.repository.HistoryRepository
import com.kkalfas.shortly.mocks.MockkTest
import io.mockk.verify
import org.junit.Test

class GetLinkHistoryUseCaseTest : MockkTest() {

    private val repository: HistoryRepository = mockk()
    private val subject = GetLinkHistoryUseCase(repository)

    @Test
    fun `when invoked then repository getLinkHistoryStream is called `() {
        // when
        subject()

        // then
        verify {
            repository.getLinkHistoryStream()
        }
    }
}
