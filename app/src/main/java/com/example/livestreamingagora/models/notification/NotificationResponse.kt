package com.example.livestreamingagora.models.notification


import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("message_id")
    val messageId: Long? = null
)