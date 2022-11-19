package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.history.api.ShrtcoApi
import com.kkalfas.shortly.data.history.model.entities.LinkEntity
import com.kkalfas.shortly.data.history.model.network.ShrtcoApiResponse
import com.kkalfas.shortly.data.history.model.network.ShrtcoApiResult
import com.kkalfas.shortly.mocks.MockkTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RemoteHistoryDataSourceTest : MockkTest() {

    private val api: ShrtcoApi = mockk()
    private val subject = RemoteHistoryDataSource(api)

    @Test
    fun `given API returns response when getShortUrl then return mapped object`() {
        // given
        val slotUrl = slot<String>()
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
        val result = runBlocking { subject.getShortUrl(givenUrl) }

        // then
        coVerify {
            api.getShortenUrl(capture(slotUrl))
        }
        assertThat(slotUrl.captured).isEqualTo(givenUrl)
        assertThat(result).isInstanceOfSatisfying(LinkEntity::class.java) {
            assertThat(it.short).isEqualTo(apiResponse.result.shortLink)
            assertThat(it.original).isEqualTo(apiResponse.result.original)
        }
    }
}
