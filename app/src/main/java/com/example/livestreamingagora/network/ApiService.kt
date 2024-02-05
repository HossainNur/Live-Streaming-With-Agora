package com.example.livestreamingagora.network

import com.example.livestreamingagora.models.DeActiveResponse
import com.example.livestreamingagora.models.LiveBody
import com.example.livestreamingagora.models.LiveResponse
import com.example.livestreamingagora.models.LoginBody
import com.example.livestreamingagora.models.LoginResponse
import com.example.livestreamingagora.models.LogoutResponse
import com.example.livestreamingagora.models.notification.NotificationRequest
import com.example.livestreamingagora.models.notification.NotificationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("live-streaming-login")
    fun userLogin(@Body body: LoginBody?): Call<LoginResponse?>?

    @POST("logout")
    fun userLogout(
        @Header("Authorization") auth: String?
    ): Call<LogoutResponse?>?

    @POST("store-new-live-streaming")
    fun userLive(@Body body: LiveBody?): Call<LiveResponse?>?

    @POST("update-steaming/{channelName}")
    fun deActiveUser(@Path("channelName") channelName: String?): Call<DeActiveResponse?>?

    @POST("fcm/send")
    fun sendNotification(
        @Header("Authorization") auth: String?, @Body request: NotificationRequest?
    ): Call<NotificationResponse?>?
}
