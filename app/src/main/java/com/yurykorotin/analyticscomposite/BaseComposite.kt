package com.yurykorotin.analyticscomposite

import com.yurykorotin.analyticscomposite.components.AnalyticsComponent
import com.yurykorotin.analyticscomposite.events.ecommerce.EcommerceBaseEvent
import com.yurykorotin.analyticscomposite.events.AnalyticsBaseEvent

class BaseComposite (
    private val components: MutableList<AnalyticsComponent> = mutableListOf()
): AnalyticsComposite() {

    override fun trackEvent(acBaseEvent: AnalyticsBaseEvent) {
        components.forEach { component ->
            component.trackEvent(acBaseEvent)
        }
    }

    override fun trackEcommerceEvent(ecommerceEvent: EcommerceBaseEvent) {
        components.forEach { component ->
            component.trackECommerceEvent(ecommerceEvent)
        }
    }

    override fun findAnalyticsComponentById(id: String): AnalyticsComponent? {
        return components.firstOrNull { it.id == id }
    }

    class CompositeBuilder {
        private val components: MutableList<AnalyticsComponent> = mutableListOf()

        fun build(): AnalyticsComposite {
            return BaseComposite(components)
        }

        fun addComponent(analyticsComponent: AnalyticsComponent): CompositeBuilder {
            components.add(analyticsComponent)

            return this
        }
    }
}