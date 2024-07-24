package com.yurykorotin.analyticscomposite.trackers

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.yurykorotin.analyticscomposite.events.*

class FirebaseTracker(context: Context, id: String) : AnalyticsTracker(id) {

    private val firebaseService = FirebaseAnalytics.getInstance(context)

    override fun trackEvent(event: AnalyticsBaseEvent) {
        when (event) {
            is ScreenOpenEvent -> {
                trackScreenEvent(event)
            }
            is SimpleEvent -> {
                trackSimpleEvent(event)
            }

            is UpdateUserPropertyEvent -> {
                setUserProperty(event)
            }

            is UserAuthEvent -> {
                trackAuthEvent(event)
            }

            else ->
                trackBaseEvent(event)
        }
    }

    private fun trackBaseEvent(event: AnalyticsBaseEvent) {
        firebaseService.logEvent(event.key, event.analyticsEventData.info)
    }

    private fun trackAuthEvent(event: UserAuthEvent) {
        firebaseService.logEvent(FirebaseAnalytics.Event.LOGIN, event.analyticsEventData.info)
    }

    private fun setUserProperty(event: UpdateUserPropertyEvent) {
        firebaseService.setUserProperty(event.name, event.value)
    }

    private fun trackSimpleEvent(event: SimpleEvent) {
        firebaseService.logEvent(event.key, event.analyticsEventData.info)
    }

    private fun trackScreenEvent(event: ScreenOpenEvent) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, event.screenName)
            putString(FirebaseAnalytics.Param.SCREEN_CLASS, event.screenClass)
        }
        firebaseService.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
}