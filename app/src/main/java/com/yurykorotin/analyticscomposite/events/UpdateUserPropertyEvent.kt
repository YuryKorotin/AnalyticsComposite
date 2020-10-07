package com.yurykorotin.analyticscomposite.events

class UpdateUserPropertyEvent(override val key: String = "",
                              val name: String,
                              val value: String,
                              override val acEventMetaData: ACEventMetaData) : ACBaseEvent