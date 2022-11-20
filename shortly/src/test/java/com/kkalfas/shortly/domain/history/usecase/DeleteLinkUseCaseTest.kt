package com.kkalfas.shortly.domain.history.usecase

import com.kkalfas.shortly.data.history.repository.HistoryRepository
import com.kkalfas.shortly.mocks.MockkTest
import io.mockk.coVerify
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DeleteLinkUseCaseTest : MockkTest() {

    private val repository: HistoryRepository = mockk()
    private val subject = DeleteLinkUseCase(repository)

    @Test
    fun `when invoked then calls repository deleteLink with param`() {
        // given
        val code = "asvs"
        val slotCode = slot<String>()

        // when
        runBlocking { subject(code) }

        // then
        coVerify {
            repository.deleteLink(capture(slotCode))
        }
        assertThat(slotCode.captured).isEqualTo(code)
    }
}
