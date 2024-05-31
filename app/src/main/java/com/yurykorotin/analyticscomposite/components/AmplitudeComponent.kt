package com.yurykorotin.analyticscomposite.components

import android.content.Context
import com.yurykorotin.analyticscomposite.events.AnalyticsBaseEvent

class AmplitudeComponent(context: Context, id: String) : AnalyticsComponent(id) {
    override fun trackEvent(acBaseEvent: AnalyticsBaseEvent) {}
}