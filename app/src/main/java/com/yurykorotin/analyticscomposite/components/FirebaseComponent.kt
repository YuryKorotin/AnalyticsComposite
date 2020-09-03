package com.yurykorotin.analyticscomposite.components

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.yurykorotin.analyticscomposite.AnalyticsParams
import com.yurykorotin.analyticscomposite.events.*

class FirebaseComponent(context: Context) : AnalyticsComponent {

    private val firebaseService = FirebaseAnalytics.getInstance(context)

    override fun trackEvent(event: ACBaseEvent) {
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

    private fun trackBaseEvent(event: ACBaseEvent) {
        firebaseService.logEvent(event.key, event.acEventMetaData.info)
    }

    private fun trackAuthEvent(event: UserAuthEvent) {
        firebaseService.logEvent(FirebaseAnalytics.Event.LOGIN, event.acEventMetaData.info)
    }

    private fun setUserProperty(event: UpdateUserPropertyEvent) {
        firebaseService.setUserProperty(AnalyticsParams.USER_ID,
                event.acEventMetaData.info.getString(ACEventMetaData.USER_ID))
        firebaseService.setUserProperty(AnalyticsParams.PROFILE_TYPE,
                event.acEventMetaData.info.getString(ACEventMetaData.USER_ID))
    }

    private fun trackSimpleEvent(event: SimpleEvent) {
        firebaseService.logEvent(event.key, event.acEventMetaData.info)
    }

    private fun trackScreenEvent(event: ScreenOpenEvent) {}
}