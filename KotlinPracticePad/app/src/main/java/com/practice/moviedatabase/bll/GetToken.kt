package com.practice.moviedatabase.bll

import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.dal.repositories.LoginRepository
import com.practice.moviedatabase.helpers.Response
import com.practice.moviedatabase.models.auth.RequestToken
import javax.inject.Inject

class GetToken @Inject constructor(
    private val repository: LoginRepository
) : UseCase<Any, Response<RequestToken>>() {

    override suspend fun execute(parameters: Any): Response<RequestToken> {
        return repository.getToken()
    }
}
