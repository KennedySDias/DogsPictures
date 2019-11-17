package com.kennedydias.dogspictures.ui.gallery.paging

import androidx.paging.DataSource
import androidx.paging.PagedList
import kotlinx.coroutines.Deferred

class PictureSourceFactory(
    private val asyncItems: Deferred<List<String>?>,
    private val onEmptyAction: () -> Unit
) : DataSource.Factory<Int, String>() {

    override fun create(): PictureKeyedDataSource {
        return PictureKeyedDataSource(asyncItems, onEmptyAction)
    }

    companion object {
        fun providePagingConfig(): PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPrefetchDistance(5)
            .setPageSize(10)
            .build()
    }

}
