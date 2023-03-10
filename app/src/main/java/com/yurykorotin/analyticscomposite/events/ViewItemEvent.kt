package com.yurykorotin.analyticscomposite.events

class ViewItemEvent(override val key: String = "",
                    override val acEventMetaData: ACEventMetaData
) : AnalyticsBaseEvent