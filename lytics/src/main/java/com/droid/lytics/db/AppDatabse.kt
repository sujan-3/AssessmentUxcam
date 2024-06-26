package com.droid.lytics.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droid.lytics.db.dao.EventDao
import com.droid.lytics.db.dao.PropDao
import com.droid.lytics.db.entity.EventEntity
import com.droid.lytics.db.entity.PropEntity

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