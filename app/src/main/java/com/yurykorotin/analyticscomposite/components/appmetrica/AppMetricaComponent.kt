package com.yurykorotin.analyticscomposite.components.appmetrica

import android.app.Application
import android.os.Bundle
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import com.yandex.metrica.ecommerce.*
import com.yurykorotin.analyticscomposite.components.AnalyticsComponent
import com.yurykorotin.analyticscomposite.events.ACEventMetaData
import com.yurykorotin.analyticscomposite.events.AnalyticsBaseEvent
import com.yurykorotin.analyticscomposite.events.ecommerce.EcommerceBaseEvent
import java.io.Serializable


class AppMetricaComponent(
    application: Application,
    apiKey: String = ""
) : AnalyticsComponent() {

    init {
        val config: YandexMetricaConfig = YandexMetricaConfig
            .newConfigBuilder(apiKey)
            .withLogs()
           .build()

        YandexMetrica.activate(application.applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(application)
        YandexMetrica.setLocationTracking(false)
    }

    override fun trackEvent(acBaseEvent: AnalyticsBaseEvent) {
        if (acBaseEvent.key.isEmpty()) {
            return
        }

        val eventParameters: MutableMap<String, Serializable?> = HashMap()
        val metaDataBundle = acBaseEvent.acEventMetaData.info

        for (key in metaDataBundle.keySet()) {
            eventParameters[key] = metaDataBundle.getSerializable(key)
        }

        YandexMetrica.reportEvent(acBaseEvent.key, eventParameters.toMap())
    }

    override fun trackECommerceEvent(ecommerceBaseEvent: EcommerceBaseEvent) {
        with(ecommerceBaseEvent.key) {
            val data = ecommerceBaseEvent.acEventMetaData.info
            val event = when (this) {
                ECommerceEvents.PurchaseEvent -> createPurchaseEvent(data)
                ECommerceEvents.BeginCheckoutEvent -> createBeginCheckoutEvent(data)
                ECommerceEvents.ShowProductCardEvent -> createShowProductCardEvent(data)
                ECommerceEvents.ShowScreenEvent -> createShowScreenEvent(data)
                ECommerceEvents.ShowProductDetailsEvent -> createShowProductDetailsEvent(data)
            } ?: return
            YandexMetrica.reportECommerce(event)
        }
    }

    private fun createPurchaseEvent(info: Bundle): ECommerceEvent? {
        val id = info.getString(ACEventMetaData.ECOMMERCE_IDENTIFIER) ?: return null
        val objectId = info.getString(ACEventMetaData.ECOMMERCE_OBJECT_ID) ?: return null
        val amount = info.getDouble(ACEventMetaData.ECOMMERCE_AMOUNT)
        val unit = info.getString(ACEventMetaData.ECOMMERCE_UNIT) ?: return null
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
        val id = info.getString(ACEventMetaData.ECOMMERCE_IDENTIFIER) ?: return null
        val objectId = info.getString(ACEventMetaData.ECOMMERCE_OBJECT_ID) ?: return null
        val amount = info.getDouble(ACEventMetaData.ECOMMERCE_AMOUNT)
        val unit = info.getString(ACEventMetaData.ECOMMERCE_UNIT) ?: return null
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
        val objectId = info.getString(ACEventMetaData.ECOMMERCE_OBJECT_ID) ?: return null
        val objectType = info.getString(ACEventMetaData.ECOMMERCE_OBJECT_TYPE)
        val ecommerceProduct = ECommerceProduct(objectId)
        val ecommerceScreen = ECommerceScreen().apply {
            name = objectId
            categoriesPath = listOf(objectType)
        }
        return ECommerceEvent.showProductCardEvent(ecommerceProduct, ecommerceScreen)
    }

    private fun createShowScreenEvent(info: Bundle): ECommerceEvent? {
        val objectId = info.getString(ACEventMetaData.ECOMMERCE_OBJECT_ID) ?: return null
        val objectType = info.getString(ACEventMetaData.ECOMMERCE_OBJECT_TYPE)
        val ecommerceScreen = ECommerceScreen().apply {
                name = objectId
                categoriesPath = listOf(objectType)
            }
        return ECommerceEvent.showScreenEvent(ecommerceScreen)
    }

    private fun createShowProductDetailsEvent(info: Bundle): ECommerceEvent? {
        val objectId = info.getString(ACEventMetaData.ECOMMERCE_OBJECT_ID) ?: return null
        val ecommerceProduct = ECommerceProduct(objectId)
        val ecommerceReferrer = ECommerceReferrer()
        return ECommerceEvent.showProductDetailsEvent(ecommerceProduct, ecommerceReferrer)
    }

}