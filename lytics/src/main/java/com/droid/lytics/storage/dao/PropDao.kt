package com.droid.lytics.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droid.lytics.storage.entity.PropEntity

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

@Dao
interface PropDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: PropEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(event: List<PropEntity>)

    @Query("SELECT * FROM props WHERE id = :propId")
    suspend fun getPropById(propId: Long): PropEntity

    @Query("SELECT * FROM props WHERE eventId = :eventId")
    suspend fun getPropsByEventId(eventId: Long): List<PropEntity>

    @Query("SELECT * FROM props")
    suspend fun getAllProps(): List<PropEntity>

    @Delete
    suspend fun deleteProp(propEntity: PropEntity)

    @Query("DELETE FROM props WHERE eventId in (:eventIds)")
    suspend fun deletePropsByEventId(eventIds: List<Long>)

    @Query("DELETE FROM props")
    suspend fun nuke()
}