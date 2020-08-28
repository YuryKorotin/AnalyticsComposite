package com.yurykorotin.analyticscomposite.components

import android.app.Application
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import com.yurykorotin.analyticscomposite.events.ACBaseEvent


class AppMetricaComponent(application: Application,
                          apiKey: String = "") : AnalyticsComponent {

    init {
        val config: YandexMetricaConfig = YandexMetricaConfig.newConfigBuilder(apiKey).build()
        YandexMetrica.activate(application.applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(application)
    }


    override fun trackEvent(acBaseEvent: ACBaseEvent) {
    }
}