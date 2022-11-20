package com.kkalfas.shortly.data.history.repository

import com.kkalfas.shortly.data.history.source.HistoryDataSource
import com.kkalfas.shortly.mocks.MockkTest
import io.mockk.coVerify
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HistoryRepositoryTest : MockkTest() {

    private val remoteDataSource: HistoryDataSource = mockk()
    private val localDataSource: HistoryDataSource = mockk()
    private val subject = HistoryRepository.Impl(remoteDataSource, localDataSource)

    @Test
    fun `when shortenUrl called then calls data source provides model`() {
        // given
        val slotUrl = slot<String>()
        val givenUrl = "http://somewhere.on/the/intra/webz"

        // when
        runBlocking { subject.shortenUrl(givenUrl) }

        // then
        coVerify {
            remoteDataSource.shortenUrl(capture(slotUrl))
        }
        assertThat(slotUrl.captured).isEqualTo(givenUrl)
    }
}
