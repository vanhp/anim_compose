package com.futurewei.myanim

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.util.Log
//import androidx  .multidex.MultiDexApplication
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
//import com.hmsecosystem.calculator.AttributionAppsFlyer.createAttributionAppsFlyer


//class App : MultiDexApplication() {
    class App(): Application() {
    override fun onCreate() {
        super.onCreate()
        appResources = resources
        val AF_DEV_KEY = appResources!!.getString(AF_DEV_KEY)


//        set single Dashboard third party store name here:
//        The value set here appears in AppsFlyer raw data install_app_store field and in
//        Overview dashboard
//        Cohort dashboard
//        Raw data reports (Raw data reports are an AppsFlyer premium feature)
        AppsFlyerLib.getInstance().setOutOfStore("AG_Connect")
        val attr: AttributionAppsFlyer = createAttributionAppsFlyer(this)
        attr.TrackEvent()
        val conversionListener: AppsFlyerConversionListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(conversionData: Map<String, Any>) {
                for (attrName in conversionData.keys) {
                    Log.d(TAG, "attribute: " + attrName + " = " + conversionData[attrName])
                }
                setInstallData(conversionData)
            }

            override fun onConversionDataFail(errorMessage: String) {
                Log.d(
                    TAG,
                    "error getting conversion data: $errorMessage"
                )
            }

            /* Called only when a Deep Link is opened */
            override fun onAppOpenAttribution(conversionData: Map<String, String>) {
                for (attrName in conversionData.keys) {
                    Log.d(TAG, "attribute: " + attrName + " = " + conversionData[attrName])
                }
            }

            override fun onAttributionFailure(errorMessage: String) {
                Log.d(TAG, "error onAttributionFailure : $errorMessage")
            }
        }

        /* This API enables AppsFlyer to detect installations, sessions, and updates. */AppsFlyerLib.getInstance()
            .init(
                AF_DEV_KEY,
                conversionListener,
                ApplicationProvider.getApplicationContext<Context>()
            )
        AppsFlyerLib.getInstance().start(this)
    }

    companion object {
        var appResources: Resources? = null
            private set
        const val TAG = "App"

        /* for checking and debugging purpose */
        var InstallConversionData = ""
        var sessionCount = 0
        fun setInstallData(conversionData: Map<String, Any>) {
            if (sessionCount == 0) {
                val install_type = """
                    Install Type: ${conversionData["af_status"]}
                    
                    """.trimIndent()
                val media_source = """
                    Media Source: ${conversionData["media_source"]}
                    
                    """.trimIndent()
                val install_time = """
                    Install Time(GMT): ${conversionData["install_time"]}
                    
                    """.trimIndent()
                val click_time = """
                    Click Time(GMT): ${conversionData["click_time"]}
                    
                    """.trimIndent()
                val is_first_launch = """
                    Is First Launch: ${conversionData["is_first_launch"]}
                    
                    """.trimIndent()
                InstallConversionData += install_type + media_source + install_time + click_time + is_first_launch
                sessionCount++
            }
        }
    }
}