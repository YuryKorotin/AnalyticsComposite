package com.yurykorotin.analyticscomposite.events

import com.yurykorotin.analyticscomposite.AnalyticsParams

class UpdateUserPropertyEvent(override val key: String = AnalyticsParams.Events.UPDATE_PROPERTY,
                              val name: String,
                              val value: String,
                              override val acEventMetaData: ACEventMetaData
) : AnalyticsBaseEvent