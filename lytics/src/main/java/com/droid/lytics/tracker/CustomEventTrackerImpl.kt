package com.droid.lytics.tracker

import com.droid.lytics.MyApplication
import com.droid.lytics.data.LyticsEvent
import com.droid.lytics.storage.entity.EventEntity
import com.droid.lytics.storage.entity.PropEntity

/**
 * Created by Sujan Rai
 * on 6/24/2024
 */
internal class CustomEventTrackerImpl : CustomEventTracker {

    private val eventDao = MyApplication.database.eventDao()
    private val propDao = MyApplication.database.propDao()

    override suspend fun trackEvent(event: LyticsEvent, sessionId: String, timestamp: Long) {
        val eventEntityId = eventDao.insert(
            EventEntity(
                name = event.eventName,
                timestamp = timestamp,
                sessionId = sessionId
            )
        )

        event.props?.map {
            PropEntity(
                name = it.key,
                value = it.value.toString(),
                timestamp = System.currentTimeMillis(),
                eventId = eventEntityId
            )
        }?.let {
            propDao.insertAll(
                it
            )
        }
    }
}