package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.FunctionalityNotAvailable
import com.kkalfas.shortly.data.history.api.ShrtcoApi
import com.kkalfas.shortly.data.history.api.model.ShrtcoApiResponse
import com.kkalfas.shortly.data.history.api.model.ShrtcoApiResult
import com.kkalfas.shortly.data.history.database.HistoryDatabaseAdapter
import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import com.kkalfas.shortly.mocks.MockkTest
import io.mockk.coEvery
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
            assertThat(this.code).isEqualTo(apiResponse.result.code)
            assertThat(this.original).isEqualTo(apiResponse.result.original)
            assertThat(this.shorted).isEqualTo(apiResponse.result.shortLink)
        }
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
}
