package com.yurykorotin.analyticscomposite

class AnalyticsParams {
    companion object {
        const val USER_ID = "user_id"
        const val PROFILE_TYPE = "profile_type"

        const val SCREEN_NAME = "screen_name"
    }

    object Events {
        const val SCREEN_OPEN = "screen_open"

        const val UPDATE_PROPERTY = "update_property"

        const val AUTH = "auth"
    }
}