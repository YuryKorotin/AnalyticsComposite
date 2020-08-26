package com.yurykorotin.analyticscomposite

import com.yurykorotin.analyticscomposite.components.AnalyticsComponent
import com.yurykorotin.analyticscomposite.events.ACBaseEvent

class BaseComposite(): AnalyticsComposite() {
    private val components: MutableList<AnalyticsComponent> = mutableListOf()

    override fun build(): AnalyticsComposite {
        components.forEach { component ->
            component.setup()
        }
        return this
    }

    override fun trackEvent(acBaseEvent: ACBaseEvent) {
        components.forEach {component ->
            component.trackEvent(acBaseEvent)
        }
    }

    override fun addComponent(analyticsComponent: AnalyticsComponent): AnalyticsComposite {
        components.add(analyticsComponent)

        return this
    }
}