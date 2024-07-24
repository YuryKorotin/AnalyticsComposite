package com.yurykorotin.analyticscomposite.events

interface AnalyticsBaseEvent : AnalyticsEvent {
    val key: String
    val analyticsEventData: AnalyticsEventData
}