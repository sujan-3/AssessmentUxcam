package com.droid.lytics.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.droid.lytics.data.EventData

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */

@Entity(tableName = "props")
data class PropEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val value: String,
    val timestamp: Long,
    val eventId: Long
) {
    fun getPropData(): EventData.Prop {
        return EventData.Prop(
            name = name,
            value = value,
            timestamp = timestamp
        )
    }
}
