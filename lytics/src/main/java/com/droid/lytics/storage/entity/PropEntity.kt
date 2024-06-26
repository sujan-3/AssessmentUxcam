package com.droid.lytics.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.droid.lytics.data.LyticsEventRequest

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
    fun getPropData(): LyticsEventRequest.Event.Prop {
        return LyticsEventRequest.Event.Prop(
            name = name,
            value = value,
            timestamp = timestamp
        )
    }
}
