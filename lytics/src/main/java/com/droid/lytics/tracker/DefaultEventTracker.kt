package com.droid.lytics.tracker

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */
internal interface DefaultEventTracker : Tracker {
    suspend fun trackAppOpen(eventName: String, timestamp: Long, sessionId: String)

    suspend fun trackScreenOpen(screenName: String, sessionId: String, timestamp: Long)
}