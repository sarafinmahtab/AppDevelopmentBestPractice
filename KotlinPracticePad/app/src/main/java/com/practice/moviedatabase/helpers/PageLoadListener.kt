package com.practice.moviedatabase.helpers

interface PageLoadListener<T> {

    fun loadFirstPage(params: T)

    fun loadNextPage(params: T)
}
