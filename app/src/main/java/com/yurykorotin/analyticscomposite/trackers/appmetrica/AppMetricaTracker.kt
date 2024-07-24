package com.yurykorotin.analyticscomposite.trackers.appmetrica

import android.app.Application
import android.os.Bundle
import com.yurykorotin.analyticscomposite.events.AnalyticsEventData
import com.yurykorotin.analyticscomposite.events.AnalyticsEventData.Companion.USER_NAME
import com.yurykorotin.analyticscomposite.events.AnalyticsBaseEvent
import com.yurykorotin.analyticscomposite.events.AppMetricaReportOpen
import com.yurykorotin.analyticscomposite.events.UpdateUserPropertyEvent
import com.yurykorotin.analyticscomposite.events.ecommerce.EcommerceBaseEvent
import com.yurykorotin.analyticscomposite.trackers.AnalyticsTracker
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
import io.appmetrica.analytics.ecommerce.ECommerceAmount
import io.appmetrica.analytics.ecommerce.ECommerceCartItem
import io.appmetrica.analytics.ecommerce.ECommerceEvent
import io.appmetrica.analytics.ecommerce.ECommerceOrder
import io.appmetrica.analytics.ecommerce.ECommercePrice
import io.appmetrica.analytics.ecommerce.ECommerceProduct
import io.appmetrica.analytics.ecommerce.ECommerceReferrer
import io.appmetrica.analytics.ecommerce.ECommerceScreen
import io.appmetrica.analytics.profile.Attribute
import io.appmetrica.analytics.profile.UserProfile
import java.io.Serializable

class AppMetricaTracker(
    private val application: Application,
    apiKey: String = "",
    id: String,
) : AnalyticsTracker(id) {

    private val config = AppMetricaConfig
        .newConfigBuilder(apiKey)
        .withLogs()
        .build()

    fun initialize() {
        AppMetrica.activate(application.applicationContext, config)
        AppMetrica.enableActivityAutoTracking(application)
        AppMetrica.setLocationTracking(false)
    }

    override fun trackEvent(event: AnalyticsBaseEvent) {
        if (event.key.isEmpty()) {
            return
        }

        when(event) {
            is UpdateUserPropertyEvent -> {
                val data = event.analyticsEventData.info
                val userId = event.value
                val name = data.getString(USER_NAME)
                val userProfile = UserProfile.newBuilder()
                    .apply(Attribute.name().withValue(name ?: userId))
                    .build()

                AppMetrica.setUserProfileID(userId)
                AppMetrica.reportUserProfile(userProfile)
            }
            is AppMetricaReportOpen -> {
                AppMetrica.reportAppOpen(event.intent)
            }
            else -> {
                val eventParameters: MutableMap<String, Serializable?> = HashMap()
                val metaDataBundle = event.analyticsEventData.info

                for (key in metaDataBundle.keySet()) {
                    eventParameters[key] = metaDataBundle.getSerializable(key)
                }

                AppMetrica.reportEvent(event.key, eventParameters.toMap())
            }
        }
    }

    override fun trackECommerceEvent(event: EcommerceBaseEvent) {
        with(event.key) {
            val data = event.analyticsEventData.info
            val event = when (this) {
                ECommerceEventKeys.PurchaseEvent -> createPurchaseEvent(data)
                ECommerceEventKeys.BeginCheckoutEvent -> createBeginCheckoutEvent(data)
                ECommerceEventKeys.ShowProductCardEvent -> createShowProductCardEvent(data)
                ECommerceEventKeys.ShowScreenEvent -> createShowScreenEvent(data)
                ECommerceEventKeys.ShowProductDetailsEvent -> createShowProductDetailsEvent(data)
                ECommerceEventKeys.AddProductEvent -> createAddProductEvent(data)
            } ?: return
            AppMetrica.reportECommerce(event)
        }
    }

    private fun createPurchaseEvent(info: Bundle): ECommerceEvent? {
        val id = info.getString(AnalyticsEventData.ECOMMERCE_IDENTIFIER) ?: return null
        val objectId = info.getString(AnalyticsEventData.ECOMMERCE_OBJECT_ID) ?: return null
        val amount = info.getDouble(AnalyticsEventData.ECOMMERCE_AMOUNT)
        val unit = info.getString(AnalyticsEventData.ECOMMERCE_UNIT) ?: return null
        val eCommerceProduct = ECommerceProduct(objectId)
        val eCommerceAmount = ECommerceAmount(amount, unit)
        val eCommercePrice = ECommercePrice(eCommerceAmount)

        val cartItems = listOf(
            ECommerceCartItem(eCommerceProduct, eCommercePrice, 1)
        )
        val ecommerceOrder = ECommerceOrder(id, cartItems)
        return ECommerceEvent.purchaseEvent(ecommerceOrder)
    }

    private fun createBeginCheckoutEvent(info: Bundle): ECommerceEvent? {
        val id = info.getString(AnalyticsEventData.ECOMMERCE_IDENTIFIER) ?: return null
        val objectId = info.getString(AnalyticsEventData.ECOMMERCE_OBJECT_ID) ?: return null
        val amount = info.getDouble(AnalyticsEventData.ECOMMERCE_AMOUNT)
        val unit = info.getString(AnalyticsEventData.ECOMMERCE_UNIT) ?: return null
        val eCommerceProduct = ECommerceProduct(objectId)
        val eCommerceAmount = ECommerceAmount(amount, unit)
        val eCommercePrice = ECommercePrice(eCommerceAmount)

        val cartItems = listOf(
            ECommerceCartItem(eCommerceProduct, eCommercePrice, 1)
        )

        val ecommerceOrder = ECommerceOrder(id, cartItems)
        return ECommerceEvent.beginCheckoutEvent(ecommerceOrder)
    }

    private fun createShowProductCardEvent(info: Bundle): ECommerceEvent? {
        val objectId = info.getString(AnalyticsEventData.ECOMMERCE_OBJECT_ID) ?: return null
        val objectType = info.getString(AnalyticsEventData.ECOMMERCE_OBJECT_TYPE)
        val ecommerceProduct = ECommerceProduct(objectId)
        val ecommerceScreen = ECommerceScreen().apply {
            name = objectId
            categoriesPath = listOf(objectType)
        }
        return ECommerceEvent.showProductCardEvent(ecommerceProduct, ecommerceScreen)
    }

    private fun createShowScreenEvent(info: Bundle): ECommerceEvent? {
        val objectId = info.getString(AnalyticsEventData.ECOMMERCE_OBJECT_ID) ?: return null
        val objectType = info.getString(AnalyticsEventData.ECOMMERCE_OBJECT_TYPE)
        val ecommerceScreen = ECommerceScreen().apply {
            name = objectId
            categoriesPath = listOf(objectType)
        }
        return ECommerceEvent.showScreenEvent(ecommerceScreen)
    }

    private fun createShowProductDetailsEvent(info: Bundle): ECommerceEvent? {
        val objectId = info.getString(AnalyticsEventData.ECOMMERCE_OBJECT_ID) ?: return null
        val ecommerceProduct = ECommerceProduct(objectId)
        val ecommerceReferrer = ECommerceReferrer()
        return ECommerceEvent.showProductDetailsEvent(ecommerceProduct, ecommerceReferrer)
    }

    private fun createAddProductEvent(info: Bundle): ECommerceEvent? {
        val objectId = info.getString(AnalyticsEventData.ECOMMERCE_OBJECT_ID) ?: return null
        val amount = info.getDouble(AnalyticsEventData.ECOMMERCE_AMOUNT)
        val unit = info.getString(AnalyticsEventData.ECOMMERCE_UNIT) ?: return null
        val eCommerceAmount = ECommerceAmount(amount, unit)
        val eCommercePrice = ECommercePrice(eCommerceAmount)
        val ecommerceProduct = ECommerceProduct(objectId)

        val addedItem = ECommerceCartItem(ecommerceProduct, eCommercePrice, 1.0)

        return ECommerceEvent.addCartItemEvent(addedItem)
    }

}