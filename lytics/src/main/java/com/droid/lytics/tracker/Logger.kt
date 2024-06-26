package com.droid.lytics.tracker

import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import com.droid.lytics.MyApplication
import com.droid.lytics.config.Config
import com.droid.lytics.data.CustomEvent
import com.droid.lytics.syncer.DataSyncHelper
import com.droid.lytics.syncer.DataSyncWorker
import com.droid.lytics.util.Constants
import com.droid.lytics.util.SessionUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
 * Created by Sujan Rai
 * on 6/23/2024
 */
class Logger {
    private val defaultEventTracker: DefaultEventTracker = DefaultEventTrackerImpl()
    private val customEventTracker: CustomEventTracker = CustomEventTrackerImpl()

    private val eventDao = MyApplication.database.eventDao()
    private val propDao = MyApplication.database.propDao()

    private var sdkInitialized = false
    private var sessionId: String? = null

    private var scope = CoroutineScope(Dispatchers.IO)

    init {
        sdkInitialized = Config.isSdkInitialized

        sessionId = Config.sessionIdentifier
    }

    /**
     * Initialize the SDK by calling this function
     */
    fun initialize() {
        Config.setSdkInitialized(true)
    }

    /**
     * Log the app open event, if the SDK is initialized
     * Marks the start of the session in the analytics
     *
     */
    internal fun logAppOpen() {
        initialize()

        if (!sdkInitialized) {
            return
        }

        scope.launch {
            Config.setSessionIdentifier(SessionUtil.generateSessionId())

            sessionId = Config.sessionIdentifier

            sessionId?.let {
                defaultEventTracker.trackAppOpen(
                    eventName = Constants.APP_OPEN,
                    sessionId = it,
                    timestamp = System.currentTimeMillis()
                )
            }
        }
    }

    internal fun logScreenView(screenName: String) {
        if (!sdkInitialized || sessionId.isNullOrEmpty())
            return

        scope.launch {
            defaultEventTracker.trackScreenOpen(
                screenName = screenName,
                sessionId = sessionId!!,
                timestamp = System.currentTimeMillis()
            )

            DataSyncHelper.fireSyncWorker()
        }
    }


    /**
     * Publicly exposed function to log events
     */
    fun logEvent(event: CustomEvent) {
        if (!sdkInitialized || sessionId.isNullOrEmpty())
            return

        scope.launch {
            customEventTracker.trackEvent(
                event = event,
                sessionId = sessionId!!,
                timestamp = System.currentTimeMillis()
            )

            DataSyncHelper.fireSyncWorker()
        }
    }
}
