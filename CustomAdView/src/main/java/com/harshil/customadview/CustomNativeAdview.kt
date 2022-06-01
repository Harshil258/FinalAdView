package com.harshil.customadview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
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
    var native_banner_ad_container: LinearLayout
    var nativeAdContainer: LinearLayout
    var customadview: CustomAdview

    constructor(
        context: Context?,
        attrs: AttributeSet?,

        ) : super(context, attrs) {
        inflate(context, R.layout.customadlayout, null)
        var view: View =
            LayoutInflater.from(context).inflate(R.layout.custom_native_adview, this, true)

        nativeadcontainer = view.findViewById(R.id.applovin_native_ad_layout)
        native_banner_ad_container = view.findViewById(R.id.native_banner_ad_container)
        customadview = view.findViewById(R.id.customadview)
        nativeAdContainer = view.findViewById<View>(R.id.native_banner_ad_container) as LinearLayout

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
        googlebanner: String
    ) {

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
                    nativeAdContainer.visibility = View.VISIBLE
                    val adView = NativeAdView.render(context, nativead)
                    nativeAdContainer.addView(adView, LayoutParams(MATCH_PARENT, WRAP_CONTENT))
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
                            if (MaxnativeAd != null) {
                                nativeAdLoader.destroy(MaxnativeAd)
                            }
                            MaxnativeAd = ad
                            nativeadcontainer.removeAllViews()
                            nativeAdContainer.visibility = View.VISIBLE
                            nativeadcontainer.addView(p0)
                        }

                        override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError) {
                            Log.d("showCustomNative", "MAXNATIVE ERROR $error")
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