package com.example.livestreamingagora.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse(
    @field:SerializedName("status")
    val status: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("data")
    val data: Data? = Data(),
    @field:SerializedName("errors")
    val errors: String? = null
)


