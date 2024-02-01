package com.example.livestreamingagora.models.notification


import com.google.gson.annotations.SerializedName

data class NotificationRequest(
    @field:SerializedName("data")
    val data: Data?,
    @field:SerializedName("to")
    val to: String?
)


data class Data(
    @SerializedName("body")
    val body: String?,
    @SerializedName("title")
    val title: String?
)