package com.yurykorotin.analyticscomposite.events

import android.app.Activity
import com.yurykorotin.analyticscomposite.AnalyticsParams

class ScreenOpenEvent(
    override val key: String = AnalyticsParams.Events.SCREEN_OPEN,
    override val acEventMetaData: ACEventMetaData,
    val activity: Activity?
) : AnalyticsBaseEvent