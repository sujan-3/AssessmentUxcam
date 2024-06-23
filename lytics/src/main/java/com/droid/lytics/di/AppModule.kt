package com.droid.lytics.di

import android.content.Context
import com.droid.lytics.MyApplication
import com.droid.lytics.db.dao.EventDao
import com.droid.lytics.tracker.DefaultTracker
import com.droid.lytics.tracker.DefaultTrackerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {

    @Provides
    fun providesDefaultTracker(
      //  eventDao: EventDao
    ): DefaultTracker {
        return DefaultTrackerImpl()
    }


    @Provides
    @Singleton
    fun provideApplicationContext(application: MyApplication): Context = application.applicationContext

}