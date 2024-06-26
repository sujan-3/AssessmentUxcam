package com.droid.lytics.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droid.lytics.storage.dao.EventDao
import com.droid.lytics.storage.dao.PropDao
import com.droid.lytics.storage.entity.EventEntity
import com.droid.lytics.storage.entity.PropEntity

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

@Database(
    entities = [
        EventEntity::class,
        PropEntity::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    internal abstract fun eventDao(): EventDao

    internal abstract fun propDao(): PropDao
}