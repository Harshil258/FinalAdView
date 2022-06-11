package com.harshil.customadview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.facebook.ads.*
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest


class CustomNativeAdview : LinearLayout {

    var attributes: TypedArray
    var nativeadcontainer: FrameLayout
    var MaxnativeAdContainer: LinearLayout
    var customadview: CustomAdview

    constructor(
        context: Context?,
        attrs: AttributeSet?,

        ) : super(context, attrs) {
        inflate(context, R.layout.customadlayout, null)
        var view: View =
            LayoutInflater.from(context).inflate(R.layout.custom_native_adview, this, true)

        nativeadcontainer = view.findViewById(R.id.applovin_native_ad_layout)
        customadview = view.findViewById(R.id.customadview)
        MaxnativeAdContainer =
            view.findViewById<View>(R.id.native_banner_ad_container) as LinearLayout

        attributes = context!!.obtainStyledAttributes(attrs, R.styleable.CustomAdview)
    }

    fun showCustomNative(
        context: Context?,
        isgoogle: Boolean,
        FBNATIVE: String,
        MAXNATIVE: String,
        fbbanner: String,
        maxbanner: String,
        appnextbanner: String,
        googlenative: String,
        googlebanner: String,
        nativeclickcounter: Int,
        nativeclick: Int
    ) {
        val nativeAdCover: LinearLayout = findViewById<LinearLayout>(R.id.nativeAdCover)

        if (isgoogle == true) {
            val adLoader = AdLoader.Builder(context, googlenative)
                .forNativeAd { nativeAd ->
                    val styles = NativeTemplateStyle.Builder().build()
                    val template: TemplateView = findViewById<TemplateView>(R.id.my_template)
                    template.setStyles(styles)
                    template.setNativeAd(nativeAd)
                    template.visibility = View.VISIBLE
                }
                .build()

            adLoader.loadAd(AdRequest.Builder().build())
        } else {

            var nativead: NativeAd
            nativead = NativeAd(context, FBNATIVE)

            var nativeAdListener = object : NativeAdListener {

                override fun onAdLoaded(p0: Ad?) {
                    Log.d("showCustomNative", "FBNATIVE LODED $FBNATIVE")

                    if (nativeclickcounter % nativeclick == 0) {
                        Log.d("nativesfdgf", nativeclickcounter.toString())
                        Log.d("nativesfdgf", "welcomee activity ads are clickable")
                    } else {
                        Log.d("nativesfdgf", "welcomee activity ads are not clickable")
                        nativeAdCover.visibility = View.VISIBLE
                    }

                    MaxnativeAdContainer.visibility = View.VISIBLE
                    val adView = NativeAdView.render(context, nativead)
                    MaxnativeAdContainer.addView(adView, LayoutParams(MATCH_PARENT, 1200))
                }

                override fun onError(p0: Ad?, p1: AdError?) {

                    Log.d("showCustomNative", "FBNATIVE ERROR ${p1.toString()}")

                    /////////////////////////////APPLOVIN NATIVE//////////////////////////////////////

                    Log.d("showCustomNative", "MAXNATIVE REQUESTED $MAXNATIVE")


                    var nativeAdLoader: MaxNativeAdLoader? = null
                    var MaxnativeAd: MaxAd? = null
                    nativeAdLoader = MaxNativeAdLoader(MAXNATIVE, context)
                    nativeAdLoader.setNativeAdListener(object : MaxNativeAdListener() {

                        override fun onNativeAdLoaded(p0: MaxNativeAdView?, ad: MaxAd?) {
                            Log.d("showCustomNative", "MAXNATIVE LODED $MAXNATIVE")
                            Log.d("showCustomNative", "MAXNATIVE AD $p0")
                            Log.d("showCustomNative", "MAXNATIVE AD ${ad!!.nativeAd.toString()}")

                            if (p0 == null) {
                                Log.d("showCustomNative", "MAXNATIVE p0 ${p0}")
                                Log.d(
                                    "showCustomNative",
                                    "MAXNATIVE ad ${ad!!.nativeAd.toString()}"
                                )
                                /////////////////////////////CUSTOM BANNER //////////////////////////////////////
                                customadview.showCustomBanner(
                                    isgoogle,
                                    fbbanner,
                                    maxbanner,
                                    appnextbanner,
                                    googlebanner
                                )
                            } else {
                                if (MaxnativeAd != null) {
                                    nativeAdLoader.destroy(MaxnativeAd)
                                }
                                if (nativeclickcounter % nativeclick == 0) {
                                    Log.d("nativesfdgf", nativeclickcounter.toString())
                                    Log.d("nativesfdgf", "welcomee activity ads are clickable")
                                } else {
                                    Log.d("nativesfdgf", "welcomee activity ads are not clickable")
                                    nativeAdCover.visibility = View.VISIBLE

                                }
                                Log.d("nativesfasdfdgf", "native ad  $ad")
                                Log.d("nativesfasdfdgf", "native ad  $p0")

                                MaxnativeAd = ad
                                nativeadcontainer.removeAllViews()
                                MaxnativeAdContainer.visibility = View.VISIBLE
                                nativeadcontainer.visibility = View.VISIBLE
                                nativeadcontainer.addView(p0)
                            }
                        }

                        override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError) {
                            Log.d("showCustomNative", "MAXNATIVE ERROR $error")
                            Log.d("showCustomNative", "MAXNATIVE ERROR CALLED BANNER $error")
                            /////////////////////////////CUSTOM BANNER //////////////////////////////////////
                            customadview.showCustomBanner(
                                isgoogle,
                                fbbanner,
                                maxbanner,
                                appnextbanner,
                                googlebanner
                            )
                            /////////////////////////////CUSTOM BANNER //////////////////////////////////////
                        }

                        override fun onNativeAdClicked(ad: MaxAd) {
                        }
                    })
                    nativeAdLoader.loadAd()

                    /////////////////////////////APPLOVIN NATIVE//////////////////////////////////////

                }

                override fun onAdClicked(p0: Ad?) {
                }

                override fun onLoggingImpression(p0: Ad?) {
                }

                override fun onMediaDownloaded(p0: Ad?) {
                }

            }

            Log.d("showCustomNative", "FBNATIVE REQUESTED $FBNATIVE")

            nativead.loadAd(
                nativead.buildLoadAdConfig().withAdListener(nativeAdListener)
                    .withMediaCacheFlag(NativeAdBase.MediaCacheFlag.ALL).build()
            )
        }

    }


}