package com.kennedydias.dogspictures.ui.fullscreen

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import com.kennedydias.dogspictures.databinding.ActivityFullScreenBinding
import com.kennedydias.dogspictures.ui.base.BaseActivity

class FullScreenPictureActivity : BaseActivity() {

    private lateinit var binding: ActivityFullScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        binding.pictureUrl = intent.getStringExtra(PARAMETER_URL_IMAGE)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
        hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    companion object {
        const val PARAMETER_URL_IMAGE = "PARAMETER_URL_IMAGE"
    }

}