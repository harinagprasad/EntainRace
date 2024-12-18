@file:Suppress("MagicNumber")

package com.app.nexttogo.utils.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormattedDate(): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy 'at' hh:mm", Locale.getDefault())
    return dateFormat.format(Date(this * 1000))
}

fun Long.toFormattedCountdown(): String {
    val hours = (this / 3600).toInt()
    val minutes = ((this % 3600) / 60).toInt()
    val seconds = (this % 60).toInt()
    return buildString {
        if (hours > 0) append("${hours}h ")
        if (minutes > 0 || hours > 0) append("${minutes}m ")
        append("${seconds}s")
    }.trim()
}