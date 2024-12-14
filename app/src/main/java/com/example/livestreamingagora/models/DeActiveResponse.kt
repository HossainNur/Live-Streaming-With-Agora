package com.example.livestreamingagora.models

import com.google.gson.annotations.SerializedName

data class DeActiveResponse(
    @field:SerializedName("status")
    val status: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("data")
    val data: Any? = null,
    @field:SerializedName("errors")
    val errors: Any? = null
)
