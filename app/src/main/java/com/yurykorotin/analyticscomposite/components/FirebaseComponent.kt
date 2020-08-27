package com.yurykorotin.analyticscomposite.components

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.yurykorotin.analyticscomposite.events.ACBaseEvent
import com.yurykorotin.analyticscomposite.events.ScreenOpenEvent
import com.yurykorotin.analyticscomposite.events.SimpleEvent

class FirebaseComponent(context: Context) : AnalyticsComponent {

    val firebase = FirebaseAnalytics.getInstance(context)

    override fun trackEvent(acBaseEvent: ACBaseEvent) {
        when (acBaseEvent) {
            is ScreenOpenEvent -> {
                trackScreenEvent(acBaseEvent)
            }
            is SimpleEvent -> {
                trackSimpleEvent(acBaseEvent)
            }
        }
    }

    private fun trackSimpleEvent(acBaseEvent: SimpleEvent) {

    }

    private fun trackScreenEvent(event: ScreenOpenEvent) {

    }
}