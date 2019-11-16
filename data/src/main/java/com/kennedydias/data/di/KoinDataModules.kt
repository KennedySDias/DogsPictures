package com.kennedydias.data.di

import com.kennedydias.data.*
import com.kennedydias.data.local.BreedsDataBase
import com.kennedydias.data.mapper.BreedMapper
import com.kennedydias.data.remote.RetrofitBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataSourceModule = module {
    single { BreedsDataBase.createDataBase(androidContext()) }
    factory<BreedsCacheDataSource> { BreedsCacheDataSourceImpl(get(), get()) }
}

val remoteDataSourceModule = module {
    single { RetrofitBuilder(androidContext().cacheDir) }
    factory<BreedsRemoteDataSource> { BreedsRemoteDataSourceImpl(get(), get()) }
}

val repositoryModule = module {
    factory<DogRepository> { DogRepositoryImpl(get(), get()) }
}

val mapperDataModule = module {
    single { BreedMapper() }
}