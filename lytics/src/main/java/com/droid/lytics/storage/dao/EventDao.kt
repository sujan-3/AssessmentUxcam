package com.droid.lytics.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droid.lytics.storage.entity.EventEntity

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(eventEntity: EventEntity): Long

    @Query("SELECT * FROM events WHERE id = :eventId")
    suspend fun getEventById(eventId: Long): EventEntity

    @Query("SELECT * FROM events")
    suspend fun getAllEvents(): List<EventEntity>

    @Delete
    suspend fun deleteEvent(eventEntity: EventEntity)

    @Query("DELETE FROM events WHERE id in (:eventIds)")
    suspend fun deleteEventsById(eventIds: List<Long>)

    @Query("DELETE FROM events")
    suspend fun nuke()
}