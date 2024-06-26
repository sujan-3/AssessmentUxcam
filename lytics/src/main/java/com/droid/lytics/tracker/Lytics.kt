package com.droid.lytics.tracker

import com.droid.lytics.config.Config
import com.droid.lytics.data.LyticsEvent
import com.droid.lytics.syncer.DataSyncHelper
import com.droid.lytics.util.Constants
import com.droid.lytics.util.SessionUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by Sujan Rai
 * on 6/23/2024
 */
object Lytics {
    private var sdkInitialized = false
    private var sessionId: String? = null

    private val defaultEventTracker: DefaultEventTracker = DefaultEventTrackerImpl()
    private val customEventTracker: CustomEventTracker = CustomEventTrackerImpl()

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
     * Opts user to disable collecting and recording events
     */
    fun deactivate() {
        Config.setSdkInitialized(false)
    }

    /**
     * Log the app open event, if the SDK is initialized
     * Marks the start of the session in the analytics
     *
     */
    internal fun logAppOpen() {
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
            async {
                defaultEventTracker.trackScreenOpen(
                    screenName = screenName,
                    sessionId = sessionId!!,
                    timestamp = System.currentTimeMillis()
                )

            }.await()

            DataSyncHelper.fireSyncWorker()
        }
    }


    /**
     * Publicly exposed function to log events
     */
    fun logEvent(event: LyticsEvent) {
        if (!sdkInitialized || sessionId.isNullOrEmpty())
            return

        scope.launch {
            async {
                customEventTracker.trackEvent(
                    event = event,
                    sessionId = sessionId!!,
                    timestamp = System.currentTimeMillis()
                )
            }.await()

            DataSyncHelper.fireSyncWorker()
        }
    }
}
