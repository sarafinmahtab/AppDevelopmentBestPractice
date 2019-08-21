package com.practice.moviedatabase.utilities

interface PageLoadListener<T> {

    fun loadFirstPage(params: T)

    fun loadNextPage(params: T)
}
