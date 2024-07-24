package com.yurykorotin.analyticscomposite

import com.yurykorotin.analyticscomposite.trackers.AnalyticsTracker
import com.yurykorotin.analyticscomposite.events.ecommerce.EcommerceBaseEvent
import com.yurykorotin.analyticscomposite.events.AnalyticsBaseEvent

abstract class AnalyticsComposite {
    abstract fun trackEvent(event: AnalyticsBaseEvent)
    abstract fun trackEcommerceEvent(event: EcommerceBaseEvent)
    abstract fun fetchAnalyticsTrackerById(id: String): AnalyticsTracker?

    companion object {
        fun create(vararg trackers: AnalyticsTracker) = object : AnalyticsComposite() {
            private val _trackers: MutableList<AnalyticsTracker> = mutableListOf()

            init {
                _trackers.addAll(trackers)
            }

            fun add(tracker: AnalyticsTracker): AnalyticsComposite {
                _trackers.add(tracker)
                return this
            }

            override fun trackEvent(acBaseEvent: AnalyticsBaseEvent) {
                _trackers.forEach { component ->
                    component.trackEvent(acBaseEvent)
                }
            }

            override fun trackEcommerceEvent(ecommerceEvent: EcommerceBaseEvent) {
                _trackers.forEach { component ->
                    component.trackECommerceEvent(ecommerceEvent)
                }
            }

            override fun fetchAnalyticsTrackerById(id: String): AnalyticsTracker? {
                return _trackers.firstOrNull { it.id == id }
            }
        }
    }
}
