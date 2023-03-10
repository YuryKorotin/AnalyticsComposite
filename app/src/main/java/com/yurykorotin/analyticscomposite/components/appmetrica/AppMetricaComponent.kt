package com.yurykorotin.analyticscomposite.components.appmetrica

import android.app.Application
import android.os.Bundle
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import com.yandex.metrica.ecommerce.ECommerceEvent
import com.yandex.metrica.ecommerce.ECommerceOrder
import com.yandex.metrica.ecommerce.ECommerceProduct
import com.yandex.metrica.ecommerce.ECommerceReferrer
import com.yandex.metrica.ecommerce.ECommerceScreen
import com.yurykorotin.analyticscomposite.events.ACEventMetaData
import com.yurykorotin.analyticscomposite.components.AnalyticsComponent
import com.yurykorotin.analyticscomposite.events.ecommerce.EcommerceBaseEvent
import com.yurykorotin.analyticscomposite.events.AnalyticsBaseEvent
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
        val ecommerceOrder = ECommerceOrder(id, listOf())
        return ECommerceEvent.purchaseEvent(ecommerceOrder)
    }

    private fun createBeginCheckoutEvent(info: Bundle): ECommerceEvent? {
        val id = info.getString(ACEventMetaData.ECOMMERCE_IDENTIFIER) ?: return null
        val ecommerceOrder = ECommerceOrder(id, listOf())
        return ECommerceEvent.beginCheckoutEvent(ecommerceOrder);
    }

    private fun createShowProductCardEvent(info: Bundle): ECommerceEvent? {
        val name = info.getString(ACEventMetaData.ECOMMERCE_NAME) ?: return null
        val ecommerceProduct = ECommerceProduct(name)
        val ecommerceScreen = ECommerceScreen()
            .setName(name)
        return ECommerceEvent.showProductCardEvent(ecommerceProduct, ecommerceScreen)
    }

    private fun createShowScreenEvent(info: Bundle): ECommerceEvent {
        val name = info.getString(ACEventMetaData.ECOMMERCE_NAME)
        val ecommerceScreen = ECommerceScreen()
            .setName(name)
        return ECommerceEvent.showScreenEvent(ecommerceScreen)
    }

    private fun createShowProductDetailsEvent(info: Bundle): ECommerceEvent? {
        val name = info.getString(ACEventMetaData.ECOMMERCE_NAME) ?: return null
        val ecommerceProduct = ECommerceProduct(name)
        val ecommerceReferrer = ECommerceReferrer()
        return ECommerceEvent.showProductDetailsEvent(ecommerceProduct, ecommerceReferrer)
    }

}