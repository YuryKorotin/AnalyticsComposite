package com.yurykorotin.analyticscomposite.events

class UserAuthEvent(
    override val analyticsEventData: AnalyticsEventData
) : AnalyticsBaseEvent {
    override val key: String = "auth"
}