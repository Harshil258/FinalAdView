package com.harshil.customadview

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import com.applovin.mediation.*
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.mediation.ads.MaxRewardedAd
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class CustomInterstitial {

    var interstitialfrequancy: Int = 1
    var interstitialcount: Int = 1

    constructor(interstitialfrequancy: Int) {
        this.interstitialfrequancy = interstitialfrequancy
    }

    fun showRewardedads(
        isgoogle: Boolean,
        activity: Activity?,
        maxrewarded: String,
        maxinterstitial: String,
        googleinterstitial: String
    ) {

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
                showInterstitial(isgoogle, activity, maxinterstitial, googleinterstitial)
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

    fun showInterstitial(
        isgoogle: Boolean,
        activity: Activity?,
        maxinterstitial: String,
        googleinterstitial: String
    ) {

        interstitialcount++

        if (isgoogle == true) {

            if (interstitialcount % interstitialfrequancy.toInt() == 0) {

                var dialog = Dialog(activity!!)
                dialog.setContentView(R.layout.customloader)
                dialog.setCancelable(false)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                val mInterstitialAd =
                    arrayOfNulls<com.google.android.gms.ads.interstitial.InterstitialAd>(1)
                val adRequest = AdRequest.Builder().build()
                com.google.android.gms.ads.interstitial.InterstitialAd.load(
                    activity,
                    googleinterstitial,
                    adRequest,
                    object : InterstitialAdLoadCallback() {
                        override fun onAdLoaded(googleinterstitialAd: com.google.android.gms.ads.interstitial.InterstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            mInterstitialAd[0] = googleinterstitialAd
                            Log.i("showInterstitial", "google interstitial onAdLoaded")
                            if (mInterstitialAd[0] != null) {
                                mInterstitialAd[0]!!.show((activity)!!)
                                dialog.dismiss()

                            } else {
                                Log.d("showInterstitial", "The interstitial ad wasn't ready yet.")
                            }
                        }

                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            // Handle the error
                            Log.i("showInterstitial", loadAdError.message)
                            mInterstitialAd[0] = null
                            dialog.dismiss()

                        }
                    })
            }
        } else {
            if (interstitialcount % interstitialfrequancy.toInt() == 0) {
                var dialog = Dialog(activity!!)
                dialog.setContentView(R.layout.customloader)
                dialog.setCancelable(false)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                lateinit var interstitialAd: MaxInterstitialAd
                interstitialAd = MaxInterstitialAd(maxinterstitial, activity)
                interstitialAd.setListener(object : MaxAdListener {
                    override fun onAdLoaded(ad: MaxAd?) {
                        interstitialAd.showAd()
                        Log.d("showInterstitial", "interstitial loded")
                        dialog.dismiss()

                    }

                    override fun onAdDisplayed(ad: MaxAd?) {
                    }

                    override fun onAdHidden(ad: MaxAd?) {
                    }

                    override fun onAdClicked(maxAd: MaxAd) {}
                    override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
                        Log.d("showInterstitial", "ad load failed interstitial ${error.toString()}")
                        dialog.dismiss()
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
}