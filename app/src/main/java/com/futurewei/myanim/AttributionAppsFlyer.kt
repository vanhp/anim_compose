package com.futurewei.myanim

import android.content.Context
import com.appsflyer.AFInAppEventParameterName
import com.appsflyer.AFInAppEventType
import com.appsflyer.AppsFlyerLib


class AttributionAppsFlyer(ctx:Context){

     var context: Context? = ctx

    fun TrackEvent() {

        /* Track Events in real time */
        val eventValue: MutableMap<String, Any> = HashMap()
        eventValue[AFInAppEventParameterName.REVENUE] = 200
        eventValue[AFInAppEventParameterName.CONTENT_TYPE] = "category_a"
        eventValue[AFInAppEventParameterName.CONTENT_ID] = "1234567"
        eventValue[AFInAppEventParameterName.CURRENCY] = "USD"
        logInAppEvent()
    }

    fun logInAppEvent() {
        Add2Wishlist()
    }

    fun Add2Wishlist() {
        val eventValues: MutableMap<String, Any> = HashMap()
        eventValues[AFInAppEventParameterName.PRICE] = 1234.56
        eventValues[AFInAppEventParameterName.CONTENT_ID] = "1234567"
        AppsFlyerLib.getInstance().logEvent(
            context,
            AFInAppEventType.ADD_TO_WISH_LIST, eventValues
        )
    }

    companion object {

        fun createAttributionAppsFlyer(ctx:Context): AttributionAppsFlyer = AttributionAppsFlyer(ctx)

    }
}