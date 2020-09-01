package com.yurykorotin.analyticscomposite.components

import android.app.Application
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import com.yurykorotin.analyticscomposite.events.ACBaseEvent
import java.io.Serializable


class AppMetricaComponent(application: Application,
                          apiKey: String = "") : AnalyticsComponent {

    init {
        val config: YandexMetricaConfig = YandexMetricaConfig.newConfigBuilder(apiKey).build()
        YandexMetrica.activate(application.applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(application)
    }


    override fun trackEvent(acBaseEvent: ACBaseEvent) {
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
}