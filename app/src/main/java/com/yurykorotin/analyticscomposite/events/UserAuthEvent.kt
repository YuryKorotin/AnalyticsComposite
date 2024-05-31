package com.yurykorotin.analyticscomposite.events

import com.yurykorotin.analyticscomposite.AnalyticsParams

data class UserAuthEvent(
    override val key: String = AnalyticsParams.Events.AUTH,
    override val acEventMetaData: ACEventMetaData
) : AnalyticsBaseEvent