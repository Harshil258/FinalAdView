package com.Harshil.customadview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.harshil.customadview.CustomAdview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var customadview : CustomAdview = findViewById(R.id.customadview)
        var fbbanner = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID"
        var appnextbanner = "7212eb01-73c9-4fc4-96e8-fad9630d4aba"
        var maxbanner = "56304bd252a6996a"
        customadview.showCustomBanner(fbbanner, appnextbanner,maxbanner)
    }
}