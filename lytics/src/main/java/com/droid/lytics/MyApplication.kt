package com.droid.lytics

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.droid.lytics.tracker.Logger
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Created by Sujan Rai
 * on 6/23/2024
 */

class MyApplication : Application() {

    private var context: Context? = null

    override fun onCreate() {
        super.onCreate()

        context = this.applicationContext

        Logger().logAppOpen()

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.d("TAG", "onCreate(): ${activity::class.java.simpleName}")

                Logger().logScreenView(activity::class.java.simpleName)

                Logger().getAllEvents()
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

    fun getContext(): Context {
        return context!!
    }
}