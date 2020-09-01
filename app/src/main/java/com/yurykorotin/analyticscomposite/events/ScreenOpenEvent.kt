package com.yurykorotin.analyticscomposite.events

import android.app.Activity

class ScreenOpenEvent(override val key: String = "",
                      override val acEventMetaData: ACEventMetaData,
                      val activity: Activity?) : ACBaseEvent