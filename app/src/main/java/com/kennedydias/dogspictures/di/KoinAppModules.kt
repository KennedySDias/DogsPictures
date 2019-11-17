package com.kennedydias.dogspictures.di

import com.kennedydias.dogspictures.ui.breeds.BreedsViewModel
import com.kennedydias.dogspictures.ui.gallery.GalleryViewModel
import com.kennedydias.dogspictures.utils.ResourcesUtils
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModules: Module = module {
    viewModel { BreedsViewModel(get()) }
    viewModel { GalleryViewModel(get(), get()) }
}

val utilsModules: Module = module {
    factory { ResourcesUtils(androidContext()) }
}