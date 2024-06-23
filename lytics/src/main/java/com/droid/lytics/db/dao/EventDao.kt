package com.droid.lytics.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droid.lytics.db.entity.Event

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event): Long

    @Query("SELECT * FROM events WHERE id = :eventId")
    suspend fun getEventById(eventId: Long): Event

    @Query("SELECT * FROM events")
    suspend fun getAllEvents(): List<Event>

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("DELETE FROM events")
    suspend fun nuke()
}