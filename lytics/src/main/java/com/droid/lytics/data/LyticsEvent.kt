package com.droid.lytics.data

/**
 * Created by Sujan Rai
 * on 6/24/2024
 */
class LyticsEvent private constructor(
    var eventName: String,
    var props: HashMap<String, Any?>? = HashMap()
) {
    data class Builder(
        private var eventName: String,
        private var props: HashMap<String, Any?>? = HashMap()
    ) {
        fun addProp(key: String, value: Any?) = apply { this.props?.put(key = key, value = value) }
        fun build() = LyticsEvent(eventName = eventName, props = this.props)
    }
}
