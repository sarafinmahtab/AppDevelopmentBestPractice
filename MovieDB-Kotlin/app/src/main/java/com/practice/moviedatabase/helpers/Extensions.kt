package com.practice.moviedatabase.helpers

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import com.practice.moviedatabase.bll.Connection
import com.practice.moviedatabase.bll.MobileNet
import com.practice.moviedatabase.bll.NoNet
import com.practice.moviedatabase.bll.WifiNet


fun Context.hasAvailableConnection(): Connection {
    val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    if (activeNetwork != null) {
        if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
            return WifiNet
        } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
            return MobileNet
        }
    } else {
        return NoNet
    }

    return NoNet
}
