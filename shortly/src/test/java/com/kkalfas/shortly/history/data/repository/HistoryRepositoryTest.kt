package com.kkalfas.shortly.history.data.repository

import com.kkalfas.shortly.history.data.source.HistoryDataSource
import com.kkalfas.shortly.mocks.MockkTest
import io.mockk.coVerify
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HistoryRepositoryTest : MockkTest() {

    private val remoteDataSource: HistoryDataSource = mockk()
    private val localDataSource: HistoryDataSource = mockk()
    private val subject = HistoryRepositoryImpl(remoteDataSource, localDataSource)

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
        coVerify(exactly = 0) {
            localDataSource.shortenUrl(any())
        }
        assertThat(slotUrl.captured).isEqualTo(givenUrl)
    }

    @Test
    fun `when deleteLink called then local data source is called`() {
        // given
        val code = "asdw"
        val slotCode = slot<String>()

        // given
        runBlocking { subject.deleteLink(code) }

        // then
        coVerify {
            localDataSource.deleteLink(capture(slotCode))
        }
        coVerify(exactly = 0) {
            remoteDataSource.deleteLink(any())
        }
        assertThat(slotCode.captured).isEqualTo(code)
    }

    @Test
    fun `when getLinkHistoryStream called local data source is called`() {
        // when
        subject.getLinkHistoryStream()

        // then
        verify {
            localDataSource.getLinkHistory()
        }
        verify(exactly = 0) {
            remoteDataSource.getLinkHistory()
        }
    }
}
