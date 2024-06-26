package com.droid.lytics

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.room.Room
import com.droid.lytics.config.Config
import com.droid.lytics.db.AppDatabase
import com.droid.lytics.tracker.Logger

/**
 * Created by Sujan Rai
 * on 6/23/2024
 */

class MyApplication : Application() {
    companion object {
        lateinit var database: AppDatabase

        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        handleDb()

        handleUser()

        Logger().logAppOpen()

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Logger().logScreenView(activity::class.java.simpleName)
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

        })
    }

    private fun handleUser() {
        if (Config.getUserId().isEmpty()){
            Config.setUserId(
                Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
            )
        }
    }

    private fun handleDb() {
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "sdk-db.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}