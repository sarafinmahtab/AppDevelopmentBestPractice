package com.practice.moviedatabase.di

import com.practice.moviedatabase.dal.networks.ApiClient
import com.practice.moviedatabase.dal.networks.ApiService
import com.practice.moviedatabase.dal.networks.ServerConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesApiService(): ApiService {
        return ApiClient.getClient(ServerConstants.BASE_URL)
            .create(ApiService::class.java)
    }
}
