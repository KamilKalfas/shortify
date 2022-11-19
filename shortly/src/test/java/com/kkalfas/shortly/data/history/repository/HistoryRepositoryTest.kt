package com.kkalfas.shortly.data.history.repository

import com.kkalfas.shortly.data.history.model.entities.LinkEntity
import com.kkalfas.shortly.data.history.source.HistoryDataSource
import com.kkalfas.shortly.mocks.MockkTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test

class HistoryRepositoryTest : MockkTest() {

    private val remoteDataSource: HistoryDataSource = mockk()
    private val subject = HistoryRepository.Impl(remoteDataSource)

    @Test
    fun `when shortenUrl called then calls data source provides model`() {
        // given
        val slotUrl = slot<String>()
        val givenUrl = "http://somewhere.on/the/intra/webz"
        val expectedEntity = LinkEntity(
            original = givenUrl,
            short = "http://its.so/sh0rt"
        )
        coEvery { remoteDataSource.getShortUrl(givenUrl) } returns expectedEntity

        // when
        val result = runBlocking { subject.shortenUrl(givenUrl) }

        // then
        coVerify {
            remoteDataSource.getShortUrl(capture(slotUrl))
        }
        Assertions.assertThat(slotUrl.captured).isEqualTo(givenUrl)
        Assertions.assertThat(result).isEqualTo(expectedEntity)
    }
}
