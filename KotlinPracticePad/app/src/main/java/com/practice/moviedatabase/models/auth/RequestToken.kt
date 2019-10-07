package com.practice.moviedatabase.models.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestToken(
    @SerializedName("success")
    @Expose
    var success: Boolean,
    @SerializedName("expires_at")
    @Expose
    var expiresAt: String,
    @SerializedName("request_token")
    @Expose
    var requestToken: String
)
