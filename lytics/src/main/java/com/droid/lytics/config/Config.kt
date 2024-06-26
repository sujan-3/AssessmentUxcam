package com.droid.lytics.config

import com.droid.lytics.MyApplication
import com.droid.lytics.UserId
import com.droid.lytics.storage.AppPreferenceManager
import com.droid.lytics.util.Constants

/**
 * Created by Sujan Rai
 * on 6/24/2024
 */
object Config {

    private var prefs: AppPreferenceManager = AppPreferenceManager.with(MyApplication.context)

    val isSdkInitialized: Boolean
        get() = prefs.getBoolean(Constants.SDK_INITIALIZED, false)

    fun setSdkInitialized(value: Boolean) {
        prefs.save(Constants.SDK_INITIALIZED, value)
    }

    val sessionIdentifier: String
        get() = prefs.getString(Constants.SESSION_IDENTIFIER, "").toString()

    fun setSessionIdentifier(sessionIdentifier: String) {
        prefs.save(Constants.SESSION_IDENTIFIER, sessionIdentifier)
    }

    fun setUserId(userId: UserId) {
        prefs.save(Constants.USER_ID, userId)
    }

    fun getUserId(): UserId {
        return prefs.getString(Constants.USER_ID, "").toString()
    }
}