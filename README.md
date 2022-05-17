# CustomAdview


Add it in your root build.gradle at the end of repositories:


	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
  Add the dependency
  
  	dependencies {
	        implementation 'com.github.Harshil258:CustomAdview:v1.0.2'
		    implementation 'com.github.Harshil258.HarshilAdView:nativetemplates:1.0.2'

	}
  
  All Variables
  
  	object Datetransfer {
        	var facebooknative: kotlin.String = "fffffffffff"
       		 var appnextbanner: kotlin.String = "fffffffffff"
       		 var fbbanner: kotlin.String = "fffffffffff"
       		 var maxbanner: kotlin.String = "fffffffffff"
       		 var maxinterstitial: kotlin.String = "fffffffffff"
      		  var maxnative: kotlin.String = "fffffffffff"
      		  var maxrewarded: kotlin.String = "fffffffffff"
      		  var disclaimer: String = "fffffffffff"
      		  var googlebanner: String = "fffffffffff"
       		 var googleinterstitial: String = "fffffffffff"
       		 var googlenative: String = "fffffffffff"
      		  var isgoogle : Boolean = false
      		  var interstitialfrequancy: Long = 1
      		  var nativefrequancy: Long = 1
      		  var adson: Boolean = true
       		 var customInterstitial: CustomInterstitial =
          	  CustomInterstitial(interstitialfrequancy.toInt())
    }
   
Custom native
    
    <com.harshil.customadview.CustomNativeAdview
        android:id="@+id/customnativead"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:maxHeight="400dp" />
   
   
   	var customnativead: CustomNativeAdview =
                    findViewById<CustomNativeAdview>(R.id.customnativead)
                customnativead.showCustomNative(
                    this,
                    isgoogle,
                    facebooknative,
                    maxnative,
                    fbbanner,
                    maxbanner,
                    appnextbanner,
                    googlenative,
                    googlebanner
                )
		
	 customInterstitial = CustomInterstitial(interstitialfrequancy.toInt())
	 
custom banner

	<com.harshil.customadview.CustomAdview
        android:id="@+id/customadview"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
	
	
  	var customadview: CustomAdview = findViewById(R.id.customadview)
        customadview.showCustomBanner(isgoogle,fbbanner, maxbanner, appnextbanner,googlebanner)
   
  	
  
  
