package com.yurykorotin.analyticscomposite.events

class ScreenOpenEvent(
    val screenName: String,
    val screenClass: String,
) : AnalyticsBaseEvent {
    override val key: String = "screen_open"
    override val analyticsEventData: AnalyticsEventData = AnalyticsEventData()
}