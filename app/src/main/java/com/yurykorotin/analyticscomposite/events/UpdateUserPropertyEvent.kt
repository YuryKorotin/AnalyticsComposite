package com.yurykorotin.analyticscomposite.events

class UpdateUserPropertyEvent(override val key: String = "",
                              override val acEventMetaData: ACEventMetaData) : ACBaseEvent