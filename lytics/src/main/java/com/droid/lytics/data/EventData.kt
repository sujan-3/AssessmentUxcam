package com.droid.lytics.data

import com.droid.lytics.UserId

/**
 * Created by Sujan Rai
 * on 6/22/2024
 */


data class LogRequest(
    val id: UserId,
    val event: List<Event>
) {
    data class Event(
        val id: Long,
        val name: String,
        val timestamp: Long,
        val sessionId: String,
        val props: List<Prop>
    ) {
        data class Prop(
            val name: String,
            val value: String,
            val timestamp: Long,
        )
    }
}
