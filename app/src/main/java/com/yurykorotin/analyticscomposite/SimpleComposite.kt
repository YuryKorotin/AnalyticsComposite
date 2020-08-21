package com.yurykorotin.analyticscomposite

class SimpleComposite: AnalyticsComposite() {
    override fun build(): AnalyticsComposite {
        return this
    }
}