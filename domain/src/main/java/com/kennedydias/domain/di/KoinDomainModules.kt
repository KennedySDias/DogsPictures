package com.kennedydias.domain.di

import com.kennedydias.domain.mapper.BreedMapper
import com.kennedydias.domain.usecase.GetAllBreedsUseCase
import com.kennedydias.domain.usecase.GetAllImagesByBreedUseCase
import org.koin.dsl.module

val mapperDomainModule = module {
    single { BreedMapper() }
}

val useCasesModule = module {
    factory { GetAllBreedsUseCase(get(), get()) }
    factory { GetAllImagesByBreedUseCase(get()) }
}
