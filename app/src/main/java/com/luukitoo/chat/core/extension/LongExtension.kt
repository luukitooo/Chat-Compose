package com.luukitoo.chat.core.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toTimeString(): String {
    val dateFormat = SimpleDateFormat("MM/dd HH:mm", Locale.getDefault())
    return dateFormat.format(Date(this))
}