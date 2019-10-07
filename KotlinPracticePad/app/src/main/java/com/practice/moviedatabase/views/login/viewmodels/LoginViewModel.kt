package com.practice.moviedatabase.views.login.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.moviedatabase.R
import com.practice.moviedatabase.base.BaseViewModel
import com.practice.moviedatabase.bll.LoginParams
import com.practice.moviedatabase.bll.PrepareLogin
import com.practice.moviedatabase.helpers.Response
import com.practice.moviedatabase.models.LoggedUser
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val prepareLogin: PrepareLogin
) : BaseViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<Response<LoggedUser>>()
    val loginResult: LiveData<Response<LoggedUser>> = _loginResult

    fun login(username: String, password: String) {

        uiScope.launch {
            val result = prepareLogin(LoginParams(username, password))
            _loginResult.value = result
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value =
                LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value =
                LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value =
                LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}

data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)
