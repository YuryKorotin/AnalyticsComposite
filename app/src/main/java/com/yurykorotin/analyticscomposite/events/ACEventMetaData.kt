package com.yurykorotin.analyticscomposite.events

import android.os.Bundle

data class ACEventMetaData(val info: Bundle) {
    companion object {
        const val USER_PROPERTY_VALUE_FIELD = "firebase_user_property"
        const val USER_ID = "user_id"

        const val USER_ROLE = "user_role"
        const val ECOMMERCE_OBJECT_TYPE: String = "object_type"
        const val ECOMMERCE_OBJECT_ID = "name"
        const val ECOMMERCE_AMOUNT: String = "amount"
        const val ECOMMERCE_IDENTIFIER: String = "order_id"
        const val ECOMMERCE_UNIT: String = "currency_code"
    }
}