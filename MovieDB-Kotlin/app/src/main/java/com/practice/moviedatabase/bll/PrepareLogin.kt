package com.practice.moviedatabase.bll

import com.google.gson.JsonObject
import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.dal.repositories.LoginRepository
import com.practice.moviedatabase.helpers.Response
import com.practice.moviedatabase.helpers.Response.Success
import com.practice.moviedatabase.helpers.Response.Error
import com.practice.moviedatabase.models.LoggedUser
import java.lang.Exception
import javax.inject.Inject

class PrepareLogin @Inject constructor(
    private var getToken: GetToken,
    private val repository: LoginRepository
) : UseCase<LoginParams, Response<LoggedUser>>() {

    override suspend fun execute(parameters: LoginParams): Response<LoggedUser> {
        val result = getToken(Any())
        return if (result is Success) {

            val jsonObject = JsonObject()
            jsonObject.addProperty("username", parameters.userName)
            jsonObject.addProperty("password", parameters.password)
            jsonObject.addProperty("request_token", result.data.requestToken)

            val session = repository.createSession(jsonObject)

            if (session is Success) {
                Success(
                    LoggedUser(
                        parameters.userName,
                        session.data.requestToken
                    )
                )
            } else {
                Error(Exception())
            }

        } else {
            Error(Exception())
        }
    }
}


data class LoginParams(
    var userName: String,
    var password: String,
    var token: String? = null
)
