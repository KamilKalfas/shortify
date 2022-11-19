package com.kkalfas.shortly.domain.history.usecase

import com.kkalfas.shortly.data.history.repository.HistoryRepository
import com.kkalfas.shortly.mocks.MockkTest
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ShortenUrlUseCaseTest : MockkTest() {

    private val repository: HistoryRepository = mockk()
    private val subject = ShortenUrlUseCase(repository)

    @Test
    fun `when invoked then repository getShortUrl called with correct param`() {
        // given
        val givenUrl = "http://somewhere.on/the/intra/webz"

        // when
        runBlocking { subject(givenUrl) }

        // then
        coVerify {
            repository.shortenUrl(givenUrl)
        }
    }
}
