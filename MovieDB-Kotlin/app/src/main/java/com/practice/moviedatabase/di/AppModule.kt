/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.practice.moviedatabase.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.practice.moviedatabase.base.MovieDB
import com.practice.moviedatabase.dal.db.AppDatabase
import com.practice.moviedatabase.dal.db.DBConstants.DATABASE_NAME
import com.practice.moviedatabase.helpers.AppConstants.PREFERENCE_NAME
import dagger.Module
import dagger.Provides

/**
 * Defines all the classes that need to be provided in the scope of the app.
 *
 * Define here all objects that are shared throughout the app, like SharedPreferences, navigators or
 * others. If some of those objects are singletons, they should be annotated with `@Singleton`.
 */
@Module
class AppModule {

    @Provides
    fun provideContext(application: MovieDB): Context {
        return application.applicationContext
    }

    @Provides
    fun provideDB(application: MovieDB): AppDatabase {
        return Room.databaseBuilder(
            application, AppDatabase::class.java, DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideSharedPreference(application: MovieDB): SharedPreferences {
        return application.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
}
