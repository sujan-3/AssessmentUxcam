package com.droid.lytics.data

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */
data class EventData(
    val name: String,
    val timestamp: Long,
    val props: List<EventProp>
) {
    data class EventProp(
        val name: String,
        val timestamp: Long,
    )
}
