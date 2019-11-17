package com.kennedydias.dogspictures.ui.gallery

import android.os.Bundle
import com.kennedydias.dogspictures.R
import com.kennedydias.dogspictures.extensions.addFragment
import com.kennedydias.dogspictures.ui.base.BaseActivity

class GalleryActivity : BaseActivity() {

    override var containerId: Int = R.id.frameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        openFragment()
    }

    private fun openFragment() {
        val fragment =
            GalleryFragment.newInstance(
                intent.getParcelableExtra(GalleryFragment.PARAMETER_BREED)
            )
        supportFragmentManager.addFragment(containerId, fragment)
    }

}