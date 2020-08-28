package com.yurykorotin.analyticscomposite

import com.yurykorotin.analyticscomposite.events.ACBaseEvent

abstract class AnalyticsComposite {
    abstract fun trackEvent(acBaseEvent: ACBaseEvent)
}
