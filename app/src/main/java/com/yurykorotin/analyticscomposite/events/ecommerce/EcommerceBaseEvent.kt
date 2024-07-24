package com.yurykorotin.analyticscomposite.events.ecommerce

import com.yurykorotin.analyticscomposite.events.AnalyticsEvent
import com.yurykorotin.analyticscomposite.trackers.appmetrica.ECommerceEventKeys
import com.yurykorotin.analyticscomposite.events.AnalyticsEventData

interface EcommerceBaseEvent : AnalyticsEvent {
    val key: ECommerceEventKeys
    val analyticsEventData: AnalyticsEventData
}