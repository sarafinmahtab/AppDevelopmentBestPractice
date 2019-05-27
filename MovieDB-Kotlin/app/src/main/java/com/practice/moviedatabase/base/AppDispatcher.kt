package com.practice.moviedatabase.base

import kotlinx.coroutines.Dispatchers

object AppDispatcher {

    val main = Dispatchers.Main
    val io = Dispatchers.IO
    val worker = Dispatchers.Default

}