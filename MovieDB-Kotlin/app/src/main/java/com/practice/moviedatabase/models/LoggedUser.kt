package com.practice.moviedatabase.models

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedUser(
    val username: String,
    val accessToken: String
)
