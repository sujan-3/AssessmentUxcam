package com.droid.lytics.tracker

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */
interface DefaultTracker : Tracker {
    suspend fun trackAppOpen()

    suspend fun trackScreenOpen(screenName: String)
}