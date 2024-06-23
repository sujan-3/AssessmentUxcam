package com.droid.lytics.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

@Entity(tableName = "props")
internal data class Prop(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val value: String,
    val eventId: Long
)
