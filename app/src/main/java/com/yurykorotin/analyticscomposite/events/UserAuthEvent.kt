package com.yurykorotin.analyticscomposite.events

data class UserAuthEvent(override val key: String = "",
                         override val acEventMetaData: ACEventMetaData) : ACBaseEvent