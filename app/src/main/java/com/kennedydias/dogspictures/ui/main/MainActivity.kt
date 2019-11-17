package com.kennedydias.dogspictures.ui.main

import android.content.Intent
import android.os.Bundle
import com.kennedydias.dogspictures.R
import com.kennedydias.dogspictures.custom.SeeMoreInterface
import com.kennedydias.dogspictures.extensions.addFragment
import com.kennedydias.dogspictures.ui.base.BaseActivity
import com.kennedydias.dogspictures.ui.breeds.BreedsFragment
import com.kennedydias.dogspictures.ui.gallery.GalleryActivity
import com.kennedydias.dogspictures.ui.gallery.GalleryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), SeeMoreInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureFragment()
    }

    override fun seeMore(breed: String) {
        if (frameLayoutPictures != null) {
            val galleryFragment = GalleryFragment.newInstance(breed)
            supportFragmentManager.addFragment(R.id.frameLayoutPictures, galleryFragment)
        } else {
            val newIntent = Intent(this, GalleryActivity::class.java)
            newIntent.putExtra(GalleryFragment.PARAMETER_BREED, breed)
            startActivity(newIntent)
        }
    }

    private fun configureFragment() {
        supportFragmentManager.addFragment(R.id.frameLayoutList, BreedsFragment.newInstance())
    }

}
