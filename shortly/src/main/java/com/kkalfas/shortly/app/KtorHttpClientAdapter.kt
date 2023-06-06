package com.kkalfas.shortly.app

import com.kkalfas.shortly.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

/**
 * Abstraction layer allowing to change [HttpClient] implementation
 */
interface KtorHttpClientAdapter {
    val client: HttpClient

    class Impl(engine: HttpClientEngine) : KtorHttpClientAdapter {
        override val client: HttpClient by lazy {
            HttpClient(engine) {
                install(UserAgent) { agent = "ktor" }
                installDefaultHeaders()
                installJSONSerialization()
                if (BuildConfig.DEBUG) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                }
            }
        }
    }
}

/**
 * Extracted to have same implementation if MockEngine used
 */
@OptIn(ExperimentalSerializationApi::class)
fun HttpClientConfig<*>.installJSONSerialization() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
        )
    }
}

fun HttpClientConfig<*>.installDefaultHeaders() {
    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}
