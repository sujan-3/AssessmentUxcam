package com.droid.lytics.syncer

import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.droid.lytics.MyApplication
import java.util.concurrent.TimeUnit

/**
 * Created by Sujan Rai
 * on 6/25/2024
 */
object DataSyncHelper {

    private const val EVENT_SYNC_WORKER = "event_sync_worker"

    private val constraint = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private val syncDataRequest = PeriodicWorkRequest.Builder(
        DataSyncWorker::class.java,
        12, TimeUnit.HOURS
    ).setConstraints(constraint)
        .addTag(EVENT_SYNC_WORKER)
        .build()

    fun fireSyncWorker() {
        Log.d("TAG", "fireSyncWorker: ")

        WorkManager
            .getInstance(MyApplication.context)
            .enqueueUniquePeriodicWork(
                EVENT_SYNC_WORKER,
                ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, syncDataRequest
            )
    }
}