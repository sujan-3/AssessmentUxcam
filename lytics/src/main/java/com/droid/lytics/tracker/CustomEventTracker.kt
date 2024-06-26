package com.droid.lytics.tracker

import com.droid.lytics.data.CustomEvent

/**
 * Created by Sujan Rai
 * on 6/24/2024
 */
internal interface CustomEventTracker: Tracker {
     suspend fun trackEvent(event: CustomEvent, sessionId: String, timestamp: Long)
}