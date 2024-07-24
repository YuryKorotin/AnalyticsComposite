package com.yurykorotin.analyticscomposite.events

data class SimpleEvent(
    override val key: String,
    override val analyticsEventData: AnalyticsEventData
) : AnalyticsBaseEvent