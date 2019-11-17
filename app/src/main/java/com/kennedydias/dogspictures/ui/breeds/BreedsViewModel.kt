package com.kennedydias.dogspictures.ui.breeds

import androidx.lifecycle.ViewModel
import com.kennedydias.dogspictures.custom.SingleLiveEvent
import com.kennedydias.domain.exception.NotConnectedException
import com.kennedydias.domain.exception.UnauthorizedException
import com.kennedydias.domain.model.BreedData
import com.kennedydias.domain.usecase.GetAllBreedsUseCase
import java.util.concurrent.TimeoutException

class BreedsViewModel(
    private val getAllBreedsUseCase: GetAllBreedsUseCase
) : ViewModel() {

    val breedsOb = SingleLiveEvent<List<BreedData>>()

    val errorOb = SingleLiveEvent<String>()
    val fatalErrorOb = SingleLiveEvent<String>()
    val notConnectedOb = SingleLiveEvent<Boolean>()
    val gettingDataOb = SingleLiveEvent<Boolean>()
    val seeMoreOb = SingleLiveEvent<BreedData>()

    fun init() {
        getBreeds(false)
    }

    fun getBreeds(forceUpdate: Boolean = false) {
        notConnectedOb.value = false
        gettingDataOb.value = true

        getAllBreedsUseCase.forceUpdate = forceUpdate
        getAllBreedsUseCase.execute {

            onComplete {
                gettingDataOb.value = false
                breedsOb.value = it
            }

            onError { error ->
                gettingDataOb.value = false
                when (error) {
                    is UnauthorizedException -> {
                        fatalErrorOb.value = error.message
                    }
                    is TimeoutException -> {
                        errorOb.value = error.message
                    }
                    is NotConnectedException -> {
                        notConnectedOb.value = true
                    }
                    else -> {
                        errorOb.value = error.message
                    }
                }
            }

            onCancel { throwable ->
                gettingDataOb.value = false
            }

        }
    }

    fun seeMore(breed: BreedData) {
        seeMoreOb.value = breed
    }

}