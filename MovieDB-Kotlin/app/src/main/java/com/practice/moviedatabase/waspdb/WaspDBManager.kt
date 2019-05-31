package com.practice.moviedatabase.waspdb

import android.content.Context
import net.rehacktive.waspdb.WaspDb
import net.rehacktive.waspdb.WaspFactory

class WaspDBManager private constructor() {

    private var waspDb: WaspDb? = null

    fun getDatabase(context: Context): WaspDb? {

        val path = context.filesDir.path
        val databaseName = "movieDB"
        val password = "1234"

        if (waspDb == null) {
            waspDb = WaspFactory.openOrCreateDatabase(path, databaseName, password)
        }

        return waspDb
    }

    companion object {

        private var manager: WaspDBManager? = null

        val instance: WaspDBManager?
            get() {

                if (manager == null) {
                    synchronized(WaspDBManager::class.java) {
                        if (manager == null) {
                            manager = WaspDBManager()
                        }
                    }
                }

                return manager
            }
    }
}
