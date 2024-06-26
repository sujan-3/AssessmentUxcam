package com.droid.lytics.tracker

import com.droid.lytics.data.LyticsEvent

/**
 * Created by Sujan Rai
 * on 6/24/2024
 */
internal interface CustomEventTracker: Tracker {
     suspend fun trackEvent(event: LyticsEvent, sessionId: String, timestamp: Long)
}