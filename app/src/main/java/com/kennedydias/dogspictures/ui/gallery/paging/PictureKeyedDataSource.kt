package com.kennedydias.dogspictures.ui.gallery.paging

import androidx.paging.ItemKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PictureKeyedDataSource(
    private val asyncItems: Deferred<List<String>?>,
    private val onEmptyAction: () -> Unit
) : ItemKeyedDataSource<Int, String>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private val items = mutableListOf<String>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<String>
    ) {
        launch {
            val items = asyncItems.await()
            items?.let {
                if (items.isEmpty()) {
                    onEmptyAction()
                }

                this@PictureKeyedDataSource.items.clear()
                this@PictureKeyedDataSource.items.addAll(items)

                val subList =
                    getSubList(items, params.requestedInitialKey ?: 0, params.requestedLoadSize)
                callback.onResult(subList)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<String>) {
        val index = params.key + 1

        val subList = getSubList(items, index, params.requestedLoadSize)
        callback.onResult(subList)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<String>) {
        val index = params.key - 1

        val subList = getSubList(items, index, params.requestedLoadSize, true)
        callback.onResult(subList)
    }

    override fun getKey(item: String): Int = items.indexOfFirst { it == item }

    private fun getSubList(
        items: List<String>,
        index: Int,
        requestedLoadSize: Int,
        before: Boolean = false
    ): List<String> {
        val fromIndex = inRange(index, 0, items.size)
        val toIndex = when {
            before -> inRange(fromIndex - requestedLoadSize, 0, items.size) // Scrolling Up
            else -> inRange(fromIndex + requestedLoadSize, 0, items.size) // Scroll Down
        }

        return if (fromIndex == toIndex) {
            emptyList()
        } else {
            items.subList(fromIndex, toIndex)
        }
    }

    private fun inRange(position: Int, start: Int, end: Int): Int {
        if (position < start) return start
        if (position > end) return end
        return position
    }

}