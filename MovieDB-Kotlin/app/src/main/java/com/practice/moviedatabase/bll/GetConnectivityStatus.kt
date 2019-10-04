package com.practice.moviedatabase.bll

import android.content.Context
import com.practice.moviedatabase.base.BaseDispatcher
import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.helpers.hasAvailableConnection
import kotlinx.coroutines.withContext

class GetConnectivityStatus(
    private val context: Context
) : UseCase<Any, Connection>() {

    override suspend fun execute(parameters: Any): Connection =
        withContext(BaseDispatcher.worker) {

            return@withContext context.hasAvailableConnection()
        }
}

sealed class Connection(val id: Int)

object WifiNet : Connection(0)
object MobileNet : Connection(1)
object NoNet : Connection(2)


fun Connection.isNetworkAvailable(): Boolean {
    return this is WifiNet || this is MobileNet
}
