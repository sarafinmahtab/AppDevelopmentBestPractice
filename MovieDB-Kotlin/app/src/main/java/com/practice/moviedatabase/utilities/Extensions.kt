package com.practice.moviedatabase.utilities

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager


fun Context.getConnectivityStatus(): Boolean {
    var status: String? = null
    val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    if (activeNetwork != null) {
        if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
            return true
        } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
            return true
        }
    } else {
        return false
    }

    return false
}
