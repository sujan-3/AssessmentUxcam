package com.droid.lytics.tracker

import android.util.Log
import com.droid.lytics.MyApplication
import com.droid.lytics.db.dao.EventDao
import com.droid.lytics.tracker.DefaultTracker
import com.droid.lytics.tracker.DefaultTrackerImpl
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Sujan Rai
 * on 6/23/2024
 */
class Logger {

    val appContext = MyApplication().getContext()
    val trackerEntryPoint =
        EntryPointAccessors.fromApplication(
            appContext, TrackerEntryPoint::class.java
        )

    /*  var defaultTracker: DefaultTracker? = null
          @Inject set

      var eventDao: EventDao? = null
          @Inject set*/

    val defaultTracker = trackerEntryPoint.defaultTracker
    val eventDao = trackerEntryPoint.eventDao

    fun logAppOpen() {
        Log.d("TAG", "logAppOpen: ")
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TAG", "logAppOpen: ")
            if (true) {
                if (defaultTracker == null) {
                    Log.d("TAG", "logAppOpen: tracker is null ")
                }
                defaultTracker?.trackAppOpen()
            }
        }
    }

    fun logScreenView(screenName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            if (true)
                defaultTracker?.trackScreenOpen(screenName)
        }
    }

    fun getAllEvents() {
        Log.d("TAG", "getAllEvents: ")
        CoroutineScope(Dispatchers.IO).launch {
            if (true) {
                val events = eventDao?.getAllEvents()

                events?.forEach {
                    Log.d("TAG", "getAllEvents: $it")
                }
            }
        }

    }


}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface TrackerEntryPoint {
    var defaultTracker: DefaultTracker
    var eventDao: EventDao

}