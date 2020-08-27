package com.yurykorotin.analyticscomposite.components

import com.yurykorotin.analyticscomposite.events.ACBaseEvent

interface AnalyticsComponent {
    fun trackEvent(acBaseEvent: ACBaseEvent)
}