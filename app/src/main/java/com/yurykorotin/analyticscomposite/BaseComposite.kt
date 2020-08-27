package com.yurykorotin.analyticscomposite

import com.yurykorotin.analyticscomposite.components.AnalyticsComponent
import com.yurykorotin.analyticscomposite.events.ACBaseEvent

class BaseComposite (
        private val components: MutableList<AnalyticsComponent> = mutableListOf()
): AnalyticsComposite() {

    override fun trackEvent(acBaseEvent: ACBaseEvent) {
        components.forEach {component ->
            component.trackEvent(acBaseEvent)
        }
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