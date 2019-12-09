package com.practice.moviedatabase.dal.repositories

import com.google.gson.JsonObject
import com.practice.moviedatabase.dal.networks.ApiService
import com.practice.moviedatabase.dal.networks.ServerConstants
import com.practice.moviedatabase.helpers.Response
import com.practice.moviedatabase.helpers.Response.Error
import com.practice.moviedatabase.helpers.Response.Success
import com.practice.moviedatabase.models.auth.RequestToken
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getToken(): Response<RequestToken> {
        return try {
            val result = apiService.getTokenAsync(ServerConstants.apiKey)
            if (result.success) {
                Success(result)
            } else {
                Error(Exception("The resource you requested could not be found."))
            }
        } catch (e: Exception) {
            Error(e)
        }
    }

    suspend fun createSession(body: JsonObject): Response<RequestToken> {
        return try {
            val result = apiService.createSessionAsync(ServerConstants.apiKey, body)
            if (result.success) {
                Success(result)
            } else {
                Error(Exception("The resource you requested could not be found."))
            }
        } catch (e: Exception) {
            Error(e)
        }
    }
}
