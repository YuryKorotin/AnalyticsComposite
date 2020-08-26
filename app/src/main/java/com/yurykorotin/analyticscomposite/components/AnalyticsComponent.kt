package com.yurykorotin.analyticscomposite.components

import com.yurykorotin.analyticscomposite.events.ACBaseEvent

interface AnalyticsComponent {
    var apiKey: String

    fun setup()
    fun trackEvent(acBaseEvent: ACBaseEvent)
}