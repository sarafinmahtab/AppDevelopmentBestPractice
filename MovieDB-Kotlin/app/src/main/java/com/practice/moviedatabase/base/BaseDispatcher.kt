package com.practice.moviedatabase.base

import kotlinx.coroutines.Dispatchers

object BaseDispatcher {

    val main = Dispatchers.Main
    val io = Dispatchers.IO
    val worker = Dispatchers.Default

}