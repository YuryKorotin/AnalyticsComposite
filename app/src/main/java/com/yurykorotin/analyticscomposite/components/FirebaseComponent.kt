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
        }
    }

    private fun setUserProperty(event: UpdateUserPropertyEvent) {
        firebaseService.setUserProperty(AnalyticsParams.USER_ID,
                event.acEventMetaData.info.getString(ACEventMetaData.USER_ID))
        firebaseService.setUserProperty(AnalyticsParams.PROFILE_TYPE,
                event.acEventMetaData.info.getString(ACEventMetaData.USER_ID))
    }

    private fun trackSimpleEvent(event: SimpleEvent) {

    }

    private fun trackScreenEvent(event: ScreenOpenEvent) {

    }
}