package com.practice.moviedatabase.custom

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PagedList
import com.practice.moviedatabase.base.AppExecutors

class ListToPagedList<T> {

    class Builder<A> {

        private val listItem = ListToPagedList<A>()
        private var list: List<A>? = null

        fun setList(list: List<A>): Builder<A> {
            this.list = list
            return this
        }

        fun build(): PagedList<A> {
            return listItem.convertFromList(list)
        }
    }

    fun convertFromList(list: List<T>?): PagedList<T> {
        Log.d("++PageData++", "List: " + list?.size)
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(20)
            .setPrefetchDistance(10)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()
        return PagedList.Builder<Int, T>(PagedDataSource(ListProvider<T>(list)), pagedListConfig)
            .setNotifyExecutor(AppExecutors.mainThread)
            .setFetchExecutor(AppExecutors.workerThread)
            .build()
    }

    class PagedDataSource<S>(private val provider: ListProvider<S>) : ItemKeyedDataSource<Int, S>() {

        var lastIndex = 0

        override fun getKey(item: S): Int {
            return lastIndex
        }

        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<S>) {
            lastIndex = 0
            val pagedData = provider.getPagedData(lastIndex, params.requestedLoadSize)
            lastIndex = pagedData.size
            callback.onResult(pagedData)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<S>) {
            val pagedData = provider.getPagedData(lastIndex, params.requestedLoadSize)
            lastIndex += pagedData.size
            callback.onResult(pagedData)
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<S>) {

        }
    }

    class ListProvider<R>(private val list: List<R>?) {

        fun getPagedData(start: Int, pageSize: Int): List<R> {

            val itemLeft = (list?.size ?: 0) - start
            val finalIndex: Int
            finalIndex = if (itemLeft < pageSize) {
                start + itemLeft
            } else {
                start + pageSize
            }

            if (list != null)
                if (start < list.size && finalIndex <= list.size && start <= finalIndex)
                    return list.subList(start, finalIndex)
            return emptyList()
        }
    }
}
