package com.yurykorotin.analyticscomposite.events

class UpdateUserPropertyEvent(
    val name: String,
    val value: String,
    override val analyticsEventData: AnalyticsEventData = AnalyticsEventData(),
) : AnalyticsBaseEvent {
    override val key: String = "update_property"
}