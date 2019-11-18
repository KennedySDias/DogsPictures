package com.kennedydias.dogspictures.ui.gallery

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kennedydias.dogspictures.R
import com.kennedydias.dogspictures.custom.SingleLiveEvent
import com.kennedydias.dogspictures.ui.gallery.paging.PictureSourceFactory
import com.kennedydias.dogspictures.utils.ResourcesUtils
import com.kennedydias.domain.exception.NotConnectedException
import com.kennedydias.domain.exception.UnauthorizedException
import com.kennedydias.domain.model.BreedData
import com.kennedydias.domain.usecase.GetAllImagesByBreedUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.util.concurrent.TimeoutException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GalleryViewModel(
    private val getAllImagesByBreedUseCase: GetAllImagesByBreedUseCase,
    private val resourcesUtils: ResourcesUtils
) : ViewModel() {

    val picturesOb = SingleLiveEvent<LiveData<PagedList<String>>>()

    val errorOb = SingleLiveEvent<String>()
    val fatalErrorOb = SingleLiveEvent<String>()
    val notConnectedOb = SingleLiveEvent<Boolean>()
    val gettingDataOb = SingleLiveEvent<Boolean>()
    val seeMoreOb = SingleLiveEvent<String>()

    fun init(arguments: Bundle?) {
        val breed = arguments?.getParcelable<BreedData>(GalleryFragment.PARAMETER_BREED)

        // Get a deferred function to fetch list
        val deferred: Deferred<List<String>?>? =
            if (breed?.key?.isNotEmpty() == true) getPictures(breed.key)
            else {
                fatalErrorOb.value = resourcesUtils.getString(R.string.something_is_wrong)
                null
            }

        deferred?.let {
            // Create a LivePagedList to create a list with auto pagination
            picturesOb.value = LivePagedListBuilder<Int, String>(
                PictureSourceFactory(
                    asyncItems = deferred,
                    onEmptyAction = {
                        // Empty state action
                        errorOb.value = resourcesUtils.getString(R.string.no_results)
                    }),
                PictureSourceFactory.providePagingConfig()
            ).build()
        }
    }

    private fun getPictures(breed: String): Deferred<List<String>?> {
        return CoroutineScope(Dispatchers.Main).async {
            suspendCoroutine<List<String>?> { continuation ->
                notConnectedOb.value = false
                gettingDataOb.value = true

                getAllImagesByBreedUseCase.breed = breed
                getAllImagesByBreedUseCase.execute {

                    onComplete {
                        gettingDataOb.value = false
                        continuation.resume(it)
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
                        continuation.resume(null)
                    }

                    onCancel { throwable ->
                        gettingDataOb.value = false
                        continuation.resume(null)
                    }

                }
            }
        }
    }

    fun seeMore(urlPicture: String) {
        seeMoreOb.value = urlPicture
    }

}