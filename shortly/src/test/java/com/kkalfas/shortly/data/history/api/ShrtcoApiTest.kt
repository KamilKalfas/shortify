package com.kkalfas.shortly.data.history.api

import com.kkalfas.shortly.data.history.api.model.ShrtcoApiResponse
import com.kkalfas.shortly.mocks.InternalServerError
import com.kkalfas.shortly.mocks.MockKtorHttpClientAdapter
import com.kkalfas.shortly.mocks.MockkTest
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ShrtcoApiTest : MockkTest() {

    private val clientAdapter = MockKtorHttpClientAdapter()
    private val subject = ShrtcoApi.Impl(clientAdapter.client)

    @Test
    fun `given success when getShortenUrl then returns success response`() {
        // given
        clientAdapter.givenSuccess()
        val url = "noop"

        // when
        val result = runBlocking { subject.getShortenUrl(url) }

        // then
        assertThat(result).isInstanceOf(ShrtcoApiResponse::class.java)

    }

    @Test(expected = InternalServerError::class)
    fun `given failure when getShortenUrl then throws InternalServerError`() {
        // given
        clientAdapter.givenFailure()
        val url = "noop"

        // when
        runBlocking { subject.getShortenUrl(url) }
    }
}
