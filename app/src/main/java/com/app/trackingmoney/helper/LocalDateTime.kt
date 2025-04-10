package com.app.trackingmoney.helper

import java.time.Duration
import java.time.LocalDateTime

fun LocalDateTime.toRelativeTimeString(): String {
    val now = LocalDateTime.now()
    val duration = Duration.between(this, now)
    return when {
        duration.toDays() >= 1 -> {
            val days = duration.toDays()
            if (days == 1L) "1 day ago" else "$days days ago"
        }
        duration.toHours() >= 1 -> {
            val hours = duration.toHours()
            if (hours == 1L) "1 hour ago" else "$hours hours ago"
        }
        duration.toMinutes() >= 1 -> {
            val minutes = duration.toMinutes()
            if (minutes == 1L) "1 minute ago" else "$minutes minutes ago"
        }
        else -> "Just now"
    }
}
