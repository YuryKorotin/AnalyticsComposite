package com.yurykorotin.analyticscomposite

import com.yurykorotin.analyticscomposite.components.AnalyticsComponent
import com.yurykorotin.analyticscomposite.events.ACBaseEvent

abstract class AnalyticsComposite {
    abstract fun build(): AnalyticsComposite
    abstract fun trackEvent(acBaseEvent: ACBaseEvent)
    abstract fun addComponent(analyticsComponent: AnalyticsComponent) : AnalyticsComposite
}
