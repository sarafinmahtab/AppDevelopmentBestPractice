package com.practice.moviedatabase.helpers

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.practice.moviedatabase.bll.Connection
import com.practice.moviedatabase.bll.MobileNet
import com.practice.moviedatabase.bll.NoNet
import com.practice.moviedatabase.bll.WifiNet

inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(provider: ViewModelProvider.Factory) =
    ViewModelProviders.of(this, provider).get(VM::class.java)


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
