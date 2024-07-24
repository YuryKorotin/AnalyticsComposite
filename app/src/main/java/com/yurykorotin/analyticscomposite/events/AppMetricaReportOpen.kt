package com.yurykorotin.analyticscomposite.events

import android.content.Intent

class AppMetricaReportOpen(val intent: Intent) : AnalyticsBaseEvent {
    override val analyticsEventData: AnalyticsEventData = AnalyticsEventData()
    override val key: String = ""
}