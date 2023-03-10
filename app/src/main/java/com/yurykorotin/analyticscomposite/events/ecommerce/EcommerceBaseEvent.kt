package com.yurykorotin.analyticscomposite.events.ecommerce

import com.yurykorotin.analyticscomposite.ACBaseEvent
import com.yurykorotin.analyticscomposite.components.appmetrica.ECommerceEvents
import com.yurykorotin.analyticscomposite.events.ACEventMetaData

interface EcommerceBaseEvent : ACBaseEvent {
    val key: ECommerceEvents
    val acEventMetaData: ACEventMetaData
}