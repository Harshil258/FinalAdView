package com.harshil.customadview

import android.app.Activity
import android.util.Log
import com.applovin.mediation.*
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.mediation.ads.MaxRewardedAd

class CustomInterstitial {

    var interstitialfrequancy: Int = 1
    var interstitialcount: Int = 1

    constructor(interstitialfrequancy: Int) {
        this.interstitialfrequancy = interstitialfrequancy
    }

    fun showRewardedads(activity: Activity?, maxrewarded: String, maxinterstitial: String) {

        lateinit var rewardedAd: MaxRewardedAd
        rewardedAd = MaxRewardedAd.getInstance(maxrewarded, activity)
        var maxRewardedAdListener = object : MaxRewardedAdListener {
            override fun onAdLoaded(ad: MaxAd?) {
                Log.d("showRewardedads", "Rewardedads loded")
                rewardedAd.showAd()
            }

            override fun onAdDisplayed(ad: MaxAd?) {
            }

            override fun onAdHidden(ad: MaxAd?) {
            }

            override fun onAdClicked(ad: MaxAd?) {
            }

            override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
                Log.d("showRewardedads", "Rewardedads Failed")
                showInterstitial(activity, maxinterstitial)
            }

            override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
            }

            override fun onRewardedVideoStarted(ad: MaxAd?) {
            }

            override fun onRewardedVideoCompleted(ad: MaxAd?) {
            }

            override fun onUserRewarded(ad: MaxAd?, reward: MaxReward?) {
            }
        }
        rewardedAd.setListener(maxRewardedAdListener)
        rewardedAd.loadAd()
    }

    fun showInterstitial(activity: Activity?, maxinterstitial: String) {

        if (interstitialcount == interstitialfrequancy.toInt()) {
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
            interstitialcount++
        }
    }
}