package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.FunctionalityNotAvailable
import com.kkalfas.shortly.data.ShrtcoApiError
import com.kkalfas.shortly.data.history.api.ShrtcoApi
import com.kkalfas.shortly.data.history.api.model.ShrtcoApiResponse
import com.kkalfas.shortly.data.history.api.model.ShrtcoApiResult
import com.kkalfas.shortly.data.history.database.HistoryDatabaseAdapter
import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import com.kkalfas.shortly.mocks.MockkTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RemoteHistoryDataSourceTest : MockkTest() {

    private val api: ShrtcoApi = mockk()
    private val databaseAdapter : HistoryDatabaseAdapter = mockk()
    private val subject = RemoteHistoryDataSource(api, databaseAdapter)

    @Test
    fun `given API returns response when shortenUrl then return mapped object`() {
        // given
        val slotUrl = slot<String>()
        val slotEntity = slot<LinkHistoryEntity>()
        val givenUrl = "http://somewhere.on/the/intra/webz"
        val apiResponse = ShrtcoApiResponse(
            ok = true,
            error = null,
            errorMessage = null,
            result = ShrtcoApiResult(
                code = "sh0rt",
                original = givenUrl,
                shortLink = "http://its.so/sh0rt"
            )
        )
        coEvery { api.getShortenUrl(givenUrl) } returns apiResponse

        // when
        runBlocking { subject.shortenUrl(givenUrl) }

        // then
        coVerifyOrder {
            api.getShortenUrl(capture(slotUrl))
            databaseAdapter.saveLink(capture(slotEntity))
        }
        assertThat(slotUrl.captured).isEqualTo(givenUrl)
        with(slotEntity.captured) {
            val result = requireNotNull(apiResponse.result)
            assertThat(this.code).isEqualTo(result.code)
            assertThat(this.original).isEqualTo(result.original)
            assertThat(this.shorted).isEqualTo(result.shortLink)
        }
    }

    @Test(expected = ShrtcoApiError::class)
    fun `given API returns error when shortenUrl then exception is thrown`() {
        // given
        val slotUrl = slot<String>()
        val givenUrl = "http://somewhere.on/the/intra/webz"
        val apiResponse = ShrtcoApiResponse(
            ok = false,
            error = 1,
            errorMessage = "error",
            result = null
        )
        coEvery { api.getShortenUrl(givenUrl) } returns apiResponse

        // when
        runBlocking { subject.shortenUrl(givenUrl) }

        // then
        coVerifyOrder {
            api.getShortenUrl(capture(slotUrl))
        }
        coVerify(exactly = 0) {
            databaseAdapter.saveLink(any())
        }
        assertThat(slotUrl.captured).isEqualTo(givenUrl)
    }

    @Test(expected = FunctionalityNotAvailable::class)
    fun `when saveLink called then FunctionalityNotAvailable exception thrown`() {
        // when
        runBlocking { subject.saveLink(mockk()) }
    }

    @Test(expected = FunctionalityNotAvailable::class)
    fun `when getLinkHistory called then FunctionalityNotAvailable exception thrown`() {
        // when
        runBlocking { subject.getLinkHistory() }
    }

    @Test(expected = FunctionalityNotAvailable::class)
    fun `when deleteLink called then FunctionalityNotAvailable exception thrown`() {
        // when
        runBlocking { subject.deleteLink("aaa") }
    }
}
