package com.example.livestreamingagora.models

import com.google.gson.annotations.SerializedName

class LogoutResponse(
    @field:SerializedName("status")
    val status: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("data")
    val data: String? = null,
    @field:SerializedName("errors")
    val errors: String? = null
)
