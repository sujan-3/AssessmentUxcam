package com.droid.lytics.tracker

import android.util.Log
import com.droid.lytics.db.dao.EventDao
import com.droid.lytics.db.entity.Event
import com.droid.lytics.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

// TODO remove constructor injection
class DefaultTrackerImpl @Inject constructor(
    //   private val eventDao: EventDao
) : DefaultTracker {

    var eventDao: EventDao? = null
        @Inject set

    override suspend fun trackAppOpen() {
        Log.d("TAG", "trackAppOpen: ")

        eventDao?.insert(
            Event(
                name = Constants.APP_OPEN,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override suspend fun trackScreenOpen(screenName: String) {
        val id = eventDao?.insert(
            Event(
                name = Constants.SCREEN_VIEW,
                timestamp = System.currentTimeMillis(),
            )
        )

        Log.d("TAG", "trackScreenOpen: $id")
    }
}