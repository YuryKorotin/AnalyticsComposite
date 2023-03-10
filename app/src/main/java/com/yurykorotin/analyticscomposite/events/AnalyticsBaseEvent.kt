package com.yurykorotin.analyticscomposite.events

import com.yurykorotin.analyticscomposite.ACBaseEvent

interface AnalyticsBaseEvent : ACBaseEvent {
    val key: String
    val acEventMetaData: ACEventMetaData
}