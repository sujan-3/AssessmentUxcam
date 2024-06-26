package com.droid.lytics.tracker

import com.droid.lytics.MyApplication
import com.droid.lytics.db.entity.EventEntity
import com.droid.lytics.db.entity.PropEntity
import com.droid.lytics.util.Constants

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

internal class DefaultEventTrackerImpl : DefaultEventTracker {
    private val eventDao = MyApplication.database.eventDao()
    private val propDao = MyApplication.database.propDao()

    override suspend fun trackAppOpen(eventName: String, timestamp: Long, sessionId: String) {
        eventDao.insert(
            EventEntity(
                name = eventName,
                timestamp = timestamp,
                sessionId = sessionId

            )
        )
    }

    override suspend fun trackScreenOpen(screenName: String, sessionId: String, timestamp: Long) {
        val id = eventDao.insert(
            EventEntity(
                name = Constants.SCREEN_VIEW,
                timestamp = timestamp,
                sessionId = sessionId
            )
        )

        propDao.insert(
            PropEntity(
                name = Constants.SCREEN_NAME,
                value = screenName,
                timestamp = timestamp,
                eventId = id
            )
        )
    }
}