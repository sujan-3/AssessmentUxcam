package com.droid.lytics.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {
    @Singleton
    @Provides
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "sdk-db.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}