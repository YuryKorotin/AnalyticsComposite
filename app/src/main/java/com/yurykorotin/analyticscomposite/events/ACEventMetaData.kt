package com.yurykorotin.analyticscomposite.events

import android.os.Bundle

data class ACEventMetaData(val info: Bundle) {
    companion object {
        const val USER_PROPERTY_VALUE_FIELD = "firebase_user_property"

        const val USER_ID = "user_id"
        const val USER_ROLE = "user_role"
    }
}