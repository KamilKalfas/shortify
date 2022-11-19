package com.kkalfas.shortly

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun Context.copyTextToClipboard(copyText: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText(null, copyText)
    clipboard.setPrimaryClip(clipData)
}
