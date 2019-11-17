package com.kennedydias.dogspictures.ui.gallery

import android.os.Bundle
import com.kennedydias.dogspictures.R
import com.kennedydias.dogspictures.ui.base.BaseActivity

class GalleryActivity : BaseActivity() {

    override var containerId: Int = R.id.frameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

}