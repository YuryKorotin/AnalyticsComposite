package com.yurykorotin.analyticscomposite.components

import com.yurykorotin.analyticscomposite.events.ecommerce.EcommerceBaseEvent
import com.yurykorotin.analyticscomposite.events.AnalyticsBaseEvent

abstract class AnalyticsComponent {
    abstract fun trackEvent(acBaseEvent: AnalyticsBaseEvent)
    open fun trackECommerceEvent(ecommerceBaseEvent: EcommerceBaseEvent) = Unit
}