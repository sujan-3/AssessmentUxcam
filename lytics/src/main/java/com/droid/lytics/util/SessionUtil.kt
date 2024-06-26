package com.droid.lytics.util

import java.util.UUID

/**
 * Created by Sujan Rai
 * on 6/24/2024
 */
object SessionUtil {
    fun generateSessionId(): String {
        return UUID.randomUUID().toString()
    }
}