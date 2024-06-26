package com.droid.lytics.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val timestamp: Long,
    val sessionId: String,
)