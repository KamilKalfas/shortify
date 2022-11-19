package com.kkalfas.shortly.mocks

import com.kkalfas.shortly.KtorHttpClientAdapter
import com.kkalfas.shortly.installDefaultHeaders
import com.kkalfas.shortly.installJSONSerialization
import com.kkalfas.shortly.mocks.response.ShortenUrlMockResponse
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*

class MockKtorHttpClientAdapter : KtorHttpClientAdapter {
    private val apiMockEngine = ApiMockEngine()
    override val client: HttpClient
        get() = apiMockEngine.client

    fun givenSuccess() = apiMockEngine.givenSuccess()
    fun givenFailure() = apiMockEngine.givenFailure()
}

class ApiMockEngine {
    val client =  HttpClient(MockEngine) {
        engine {
            addHandler { request -> handleSearchRequest(request) ?: errorResponse() }
        }
        installDefaultHeaders()
        installJSONSerialization()
    }

    private var isSuccess: Boolean? = null
        get() = field ?: throw IllegalStateException("Mock has not beet initialized")

    fun givenSuccess() {
        isSuccess = true
    }

    fun givenFailure() {
        isSuccess = false
    }

    private fun MockRequestHandleScope.handleSearchRequest(
        request: HttpRequestData
    ): HttpResponseData? {

        val statusCode = if (isSuccess == true) {
            HttpStatusCode.OK
        } else {
            HttpStatusCode.InternalServerError
        }

        return respond(
            content = mockedResponses(request),
            status = statusCode,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    private fun mockedResponses(request: HttpRequestData): String {
        val encodedPath = request.url.encodedPath
        return when {
            isSuccess == false -> throw InternalServerError()
            encodedPath.contains("/v2/shorten") -> ShortenUrlMockResponse()
            else -> error("Unhandled $encodedPath")
        }
    }

    private fun MockRequestHandleScope.errorResponse(): HttpResponseData {
        return respond(
            content = "",
            status = HttpStatusCode.BadRequest,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }
}

class InternalServerError : Exception("InternalServerError")
