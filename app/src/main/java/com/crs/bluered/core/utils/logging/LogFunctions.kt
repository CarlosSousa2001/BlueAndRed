package com.crs.bluered.core.utils.logging

import timber.log.Timber

fun logError(tag: String, message: String) {
    Timber.tag(tag).e("Error -> $message")
}

fun logInfo(tag: String, message: String) {
    Timber.tag(tag).i("Info -> $message")
}