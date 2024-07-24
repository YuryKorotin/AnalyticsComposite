package com.yurykorotin.analyticscomposite.events

import android.os.Bundle
import androidx.core.os.bundleOf

data class AnalyticsEventData(val info: Bundle = bundleOf()) {
    companion object {
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"

        const val ECOMMERCE_OBJECT_TYPE: String = "object_type"
        const val ECOMMERCE_OBJECT_ID = "name"
        const val ECOMMERCE_AMOUNT: String = "amount"
        const val ECOMMERCE_IDENTIFIER: String = "order_id"
        const val ECOMMERCE_UNIT: String = "currency_code"
    }
}