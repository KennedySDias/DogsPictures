package com.kennedydias.dogspictures.di

import com.kennedydias.dogspictures.ui.breeds.BreedsViewModel
import com.kennedydias.dogspictures.ui.gallery.GalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModules: Module = module {
    viewModel { BreedsViewModel(get()) }
    viewModel { GalleryViewModel() }
}