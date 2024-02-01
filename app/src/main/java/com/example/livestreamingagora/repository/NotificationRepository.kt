package com.example.livestreamingagora.repository

import android.util.Log
import com.example.livestreamingagora.SERVICE_KEY_NOTIFICATION
import com.example.livestreamingagora.models.notification.NotificationRequest
import com.example.livestreamingagora.models.notification.NotificationResponse
import com.example.livestreamingagora.network.ApiNotification.Companion.instance
import com.example.livestreamingagora.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationRepository {
    private val TAG = "Notification"
    private val apiService: ApiService

    init {
        apiService = instance!!.apiService
    }

    fun sendNotification(callBack: NotificationCallBack, body: NotificationRequest?){
        val call = apiService.sendNotification(SERVICE_KEY_NOTIFICATION, body)
        call?.enqueue(object : Callback<NotificationResponse?>{
            override fun onResponse(
                call: Call<NotificationResponse?>,
                response: Response<NotificationResponse?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Log.v(TAG, response.body().toString())
                    callBack.onResponse(response.body())
                } else {
                    Log.v(TAG, response.message())
                    callBack.onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<NotificationResponse?>, t: Throwable) {
                Log.v(TAG, "onFailure: " + t.message)
                callBack.onFailure(t.message)                    }

        })
    }


    interface NotificationCallBack {
        fun onResponse(response: NotificationResponse?)
        fun onFailure(message: String?)
    }
}