package com.yurykorotin.analyticscomposite.trackers

import com.yurykorotin.analyticscomposite.events.ecommerce.EcommerceBaseEvent
import com.yurykorotin.analyticscomposite.events.AnalyticsBaseEvent

abstract class AnalyticsTracker(val id: String) {
    abstract fun trackEvent(event: AnalyticsBaseEvent)
    open fun trackECommerceEvent(event: EcommerceBaseEvent) = Unit
}