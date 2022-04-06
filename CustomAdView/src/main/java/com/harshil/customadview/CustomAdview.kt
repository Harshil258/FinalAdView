package com.harshil.customadview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import com.appnext.banners.BannerAdRequest
import com.appnext.banners.BannerSize
import com.appnext.banners.BannerView
import com.appnext.core.AppnextAdCreativeType
import com.appnext.core.AppnextError
import com.facebook.ads.*
import com.unity3d.services.banners.api.Banner

class CustomAdview : LinearLayout {

    var attributes: TypedArray
    var adLinearContainer: LinearLayout
    var bannerView : BannerView
    val webview_banner: FrameLayout

    constructor(
        context: Context?,
        attrs: AttributeSet?,

        ) : super(context, attrs) {
//        inflate(context, R.layout.customadlayout, null)
        var view: View = LayoutInflater.from(context).inflate(R.layout.customadlayout, this, true)

        adLinearContainer = view.findViewById(R.id.fb_banner_ad_container)
        webview_banner = view.findViewById(R.id.applovinbanner_first)
        bannerView = view.findViewById(R.id.banner)

        attributes = context!!.obtainStyledAttributes(attrs, R.styleable.CustomAdview)
    }

    fun showCustomBanner(
        FBbanner: String,
        maxbanner: String,
        appnextbanner: String
    ) {
        var bannerAdfb: AdView =
            AdView(context, FBbanner, AdSize.BANNER_HEIGHT_90)
        adLinearContainer.removeAllViewsInLayout()
        adLinearContainer.addView(bannerAdfb)
        Log.d("adsfgsdfhadgsdg", "webview banner call")
        Log.d("adsfgsdfhadgsdg", FBbanner)

        val adListener: AdListener = object : AdListener {
            override fun onError(ad: Ad?, adError: AdError) {
                Log.d("adsfgsdfhadgsdg", "error")

                var applovinads =
                    MaxAdView(maxbanner, context)
                applovinads.setExtraParameter("adaptive_banner", "true")



                applovinads.setListener(object : MaxAdViewAdListener {
                    override fun onAdLoaded(ad: MaxAd?) {
                        webview_banner.visibility = View.VISIBLE
                        Log.d("applovinads", "adloded ${ad.toString()}")
                    }

                    override fun onAdDisplayed(ad: MaxAd?) {
                    }

                    override fun onAdHidden(ad: MaxAd?) {
                    }

                    override fun onAdClicked(ad: MaxAd?) {
                    }

                    override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
                        Log.d("applovinads", "ad load failed ${error.toString()}")

                        val banner = BannerView(context)
                        banner.setPlacementId(appnextbanner)
                        banner.setBannerSize(BannerSize.LARGE_BANNER)

                        bannerView.setPlacementId(appnextbanner)
                        bannerView.setBannerSize(BannerSize.LARGE_BANNER)
                        bannerView.loadAd(BannerAdRequest())

                        bannerView.setBannerListener(object :
                            com.appnext.banners.BannerListener() {
                            override fun onAdLoaded(
                                p0: String?,
                                p1: AppnextAdCreativeType?
                            ) {
                                super.onAdLoaded(p0, p1)
                                Log.d("adsfgsaedgdgdgsdg", "    Lappnext  ")
                                bannerView.visibility = View.VISIBLE
                            }

                            override fun onError(p0: AppnextError?) {
                                super.onError(p0)
                                Log.d("adsfgsaedgdgdgsdg", p0?.errorMessage.toString())
                            }
                        })
                    }

                    override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
                    }

                    override fun onAdExpanded(ad: MaxAd?) {
                    }

                    override fun onAdCollapsed(ad: MaxAd?) {
                    }

                })
                webview_banner.addView(applovinads)
                applovinads.loadAd()

            }

            override fun onAdLoaded(ad: Ad?) {
                Log.d("adsfgsdfhadgsdg", "loded")
                adLinearContainer.visibility = View.VISIBLE
            }

            override fun onAdClicked(ad: Ad?) {
            }

            override fun onLoggingImpression(ad: Ad?) {
            }
        }
        bannerAdfb.loadAd(bannerAdfb.buildLoadAdConfig().withAdListener(adListener).build())
    }

}