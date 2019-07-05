package com.practice.moviedatabase.dal

interface PageLoadListener<T> {

    fun loadFirstPage(params: T)

    fun loadNextPage(params: T)
}
