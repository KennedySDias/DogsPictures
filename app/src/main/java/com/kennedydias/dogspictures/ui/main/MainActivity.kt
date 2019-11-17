package com.kennedydias.dogspictures.ui.main

import android.os.Bundle
import com.kennedydias.dogspictures.R
import com.kennedydias.dogspictures.extensions.addFragment
import com.kennedydias.dogspictures.ui.base.BaseActivity
import com.kennedydias.dogspictures.ui.breeds.BreedsFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureFragment()
    }

    private fun configureFragment() {
        supportFragmentManager.addFragment(R.id.frameLayoutList, BreedsFragment.newInstance())
    }

}
