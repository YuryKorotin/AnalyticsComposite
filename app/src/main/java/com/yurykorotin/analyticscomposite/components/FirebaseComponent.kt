package com.yurykorotin.analyticscomposite.components

import com.yurykorotin.analyticscomposite.events.ACBaseEvent

abstract class  FirebaseComponent(override var apiKey: String) : AnalyticsComponent {
    abstract fun logEvent(acBaseEvent: ACBaseEvent)
}