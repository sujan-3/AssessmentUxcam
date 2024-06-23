package com.droid.lytics.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droid.lytics.db.dao.EventDao
import com.droid.lytics.db.entity.Event
import com.droid.lytics.db.entity.Prop

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

@Database(
    entities = [
        Event::class,
        Prop::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    internal abstract fun eventDao(): EventDao
}