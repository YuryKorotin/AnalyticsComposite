package com.yurykorotin.analyticscomposite

import com.yurykorotin.analyticscomposite.components.AnalyticsComponent
import com.yurykorotin.analyticscomposite.events.ecommerce.EcommerceBaseEvent
import com.yurykorotin.analyticscomposite.events.AnalyticsBaseEvent

abstract class AnalyticsComposite {
    abstract fun trackEvent(acBaseEvent: AnalyticsBaseEvent)
    abstract fun trackEcommerceEvent(ecommerceEvent: EcommerceBaseEvent)
    abstract fun findAnalyticsComponentById(id: String): AnalyticsComponent?
}
