package com.harshil.customadview

import android.app.Activity
import android.util.Log
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd

class CustomInterstitial {
    fun showInterstitial(activity: Activity?, maxinterstitial: String) {
        lateinit var interstitialAd: MaxInterstitialAd
        interstitialAd = MaxInterstitialAd(maxinterstitial, activity)
        interstitialAd.setListener(object : MaxAdListener {
            override fun onAdLoaded(ad: MaxAd?) {
                interstitialAd.showAd()
                Log.d("showInterstitial", "interstitial loded")
            }

            override fun onAdDisplayed(ad: MaxAd?) {
            }

            override fun onAdHidden(ad: MaxAd?) {
            }

            override fun onAdClicked(maxAd: MaxAd) {}
            override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
                Log.d("showInterstitial", "ad load failed interstitial ${error.toString()}")
            }

            override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
                interstitialAd.loadAd()
            }
        })
        interstitialAd.loadAd()
    }
}