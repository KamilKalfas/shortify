package com.kkalfas.shortly.data.history.model.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
{
    "ok": true,
    "result": {
        "code": "AHkW47",
        "short_link": "shrtco.de\/AHkW47",
        "full_short_link": "https:\/\/shrtco.de\/AHkW47",
        "short_link2": "9qr.de\/AHkW47",
        "full_short_link2": "https:\/\/9qr.de\/AHkW47",
        "short_link3": "shiny.link\/AHkW47",
        "full_short_link3": "https:\/\/shiny.link\/AHkW47",
        "share_link": "shrtco.de\/share\/AHkW47",
        "full_share_link": "https:\/\/shrtco.de\/share\/AHkW47",
        "original_link": "https:\/\/itch.io\/jam\/20-second-game-jam"
    }
}
**/

@Serializable
data class ShrtcoApiResponse(
    @SerialName("ok") val ok: Boolean,
    @SerialName("result") val result: ShrtcoApiResult
)

@Serializable
data class ShrtcoApiResult(
    @SerialName("code") val code: String,
    @SerialName("full_short_link") val shortLink: String,
    @SerialName("original_link") val original: String
)
