package com.yurykorotin.analyticscomposite.events

interface ACBaseEvent {
    val key: String
    val acEventMetaData: ACEventMetaData
}