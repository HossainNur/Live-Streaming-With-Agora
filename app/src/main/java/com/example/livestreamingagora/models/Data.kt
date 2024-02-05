package com.example.livestreamingagora.models

import com.google.gson.annotations.SerializedName

data class Data(
    @field:SerializedName("access_token")
    val accessToken: String? = null,
    @field:SerializedName("token_type")
    val tokenType: String? = null,
    @field:SerializedName("expires_in")
    val expiresIn: Int? = 0
)
