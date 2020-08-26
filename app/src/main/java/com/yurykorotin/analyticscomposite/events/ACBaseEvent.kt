package com.yurykorotin.analyticscomposite.events

import java.util.*

interface ACBaseEvent {
    val key: String
    val acEventMetaData: ACEventMetaData
}