package com.kennedydias.dogspictures

import android.app.Application
import com.kennedydias.data.di.cacheDataSourceModule
import com.kennedydias.data.di.mapperDataModule
import com.kennedydias.data.di.remoteDataSourceModule
import com.kennedydias.data.di.repositoryModule
import com.kennedydias.dogspictures.di.utilsModules
import com.kennedydias.dogspictures.di.viewModelModules
import com.kennedydias.domain.di.mapperDomainModule
import com.kennedydias.domain.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DogsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin() {
        val appModules = listOf(
            // Data
            cacheDataSourceModule,
            remoteDataSourceModule,
            repositoryModule,
            mapperDataModule,

            // Domain
            mapperDomainModule,
            useCasesModule,

            // Presentation
            viewModelModules,
            utilsModules
        )

        startKoin {
            // Android context
            androidContext(this@DogsApplication)
            // modules
            modules(appModules)
        }
    }

}