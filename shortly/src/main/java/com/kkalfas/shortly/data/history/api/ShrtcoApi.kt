package com.kkalfas.shortly.data.history.api
import com.kkalfas.shortly.data.history.api.model.ShrtcoApiResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

private const val HOST = "api.shrtco.de"
private const val PATH_SHORTEN = "v2/shorten"
private const val PARAM_SHORTEN = "url"

interface ShrtcoApi {
    suspend fun getShortenUrl(originalUrl: String) : ShrtcoApiResponse

    class Impl @Inject constructor(
        private val client: HttpClient
    ) : ShrtcoApi {
        override suspend fun getShortenUrl(originalUrl: String): ShrtcoApiResponse {
            val response = client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST
                    path(PATH_SHORTEN)
                    parameters.append(PARAM_SHORTEN, originalUrl)
                }
            }
            return response.body()
        }
    }
}
