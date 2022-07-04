package com.Harshil.customadview

import android.app.Activity
import android.app.Application
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.harshil.customadview.CustomAdview
import com.harshil.customadview.CustomInterstitial

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var customadview: CustomAdview = findViewById(R.id.customadview)
        var fbbanner = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID"
        var appnextbanner = "fsdsfzsfhgf"
        var maxbanner = "56304bd252a6996a"
        var googlebanner = "ca-app-pub-3940256099942544/6300978111"
        var googleinterstitial: String = "ca-app-pub-3940256099942544/1033173712"

//        customadview.showCustomBanner(true, fbbanner, maxbanner, appnextbanner, googlebanner)

//        AppLovinSdk.getInstance(this).showMediationDebugger()
//
        var customInterstitial: CustomInterstitial =
            CustomInterstitial(1)

        val activityLifecycleCallbacks: Application.ActivityLifecycleCallbacks =
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                    TODO("Not yet implemented")
                }

                override fun onActivityStarted(p0: Activity) {
                    Log.e(TAG, "onActivityStarted: " + p0.toString());
                }

                override fun onActivityResumed(p0: Activity) {
                    TODO("Not yet implemented")
                }

                override fun onActivityPaused(p0: Activity) {
                    TODO("Not yet implemented")
                }

                override fun onActivityStopped(p0: Activity) {
                    TODO("Not yet implemented")
                }

                override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                    TODO("Not yet implemented")
                }

                override fun onActivityDestroyed(p0: Activity) {
                    Log.e(TAG, "onActivityDestroyed: " + p0.localClassName);
                }

            }

        this.getInstance().registerActivityLifecycleCallbacks(activityLifecycleCallbacks)



        customInterstitial.showInterstitial(
            false,
            this,
            "4e2abcad1149a077",
            googleinterstitial
        )

//        var customnativead: CustomNativeAdview =
//            findViewById<CustomNativeAdview>(R.id.customnativead)
//        customnativead.showCustomNative(
//            this,
//            false,
//            "sdrtyjdyjdyjk",
//            "98ccfa1dbd2cee76",
//            fbbanner,
//            maxbanner,
//            appnextbanner,
//            "ca-app-pub-3940256099942544/2247696110",
//            googlebanner,
//            1,
//            100
//        )

    }
}