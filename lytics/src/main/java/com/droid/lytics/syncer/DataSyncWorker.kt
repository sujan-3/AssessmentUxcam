package com.droid.lytics.syncer

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.droid.lytics.MyApplication
import com.droid.lytics.config.Config
import com.droid.lytics.data.LyticsEventRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Sujan Rai
 * on 6/25/2024
 */
class DataSyncWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val eventDao = MyApplication.database.eventDao()
    private val propDao = MyApplication.database.propDao()

    override suspend fun doWork(): Result {
        return syncData()
    }

    private suspend fun syncData(): Result {
        try {
            val logRequest = getData()

            Log.d("TAG", "syncData(): $logRequest")

            CoroutineScope(Dispatchers.IO).launch {
                // Simulating network call here
                delay(5000)

                val success = true

                // Remove events and properties that are synced successfully
                if (success) {
                    val eventIds = logRequest.event.map { it.id }
                    eventDao.deleteEventsById(eventIds)

                    propDao.deletePropsByEventId(eventIds)
                }
            }

            return Result.success()

        } catch (e: Exception) {
            Log.e("TAG", e.localizedMessage)

            return Result.failure()
        }
    }

    private suspend fun getData(): LyticsEventRequest {
        val eventEntities = eventDao.getAllEvents()

        if (eventEntities.isEmpty()) {
            throw Exception("No events found")
        }

        val userId = Config.getUserId()

        val events = mutableListOf<LyticsEventRequest.Event>()

        eventEntities.forEach { it ->
            val props = propDao.getPropsByEventId(it.id)

            val event = LyticsEventRequest.Event(
                id = it.id,
                name = it.name,
                timestamp = it.timestamp,
                sessionId = it.sessionId,
                props = props.map {
                    it.getPropData()
                }.toList()
            )

            events.add(event)
        }

        val lyticsEventRequest = LyticsEventRequest(
            id = userId,
            event = events
        )

        return lyticsEventRequest
    }
}