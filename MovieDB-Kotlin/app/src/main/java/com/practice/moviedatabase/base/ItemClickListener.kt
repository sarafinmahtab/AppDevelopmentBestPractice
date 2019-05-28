package com.practice.moviedatabase.base

import com.practice.moviedatabase.models.Result

interface ItemClickListener {

    fun onItemClicked(result: Result, outputDate: String?)
}
