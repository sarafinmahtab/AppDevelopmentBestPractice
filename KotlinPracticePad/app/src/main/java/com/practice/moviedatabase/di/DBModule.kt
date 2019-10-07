package com.practice.moviedatabase.di

import com.practice.moviedatabase.dal.db.AppDao
import com.practice.moviedatabase.dal.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DBModule {

    @Provides
    fun providesAppDao(parameter: AppDatabase): AppDao {
        return parameter.getAppDao()
    }
}
