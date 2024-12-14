package com.example.livestreamingagora.repository

import android.app.Application
import com.example.livestreamingagora.models.LiveBody
import com.example.livestreamingagora.models.LiveResponse
import com.example.livestreamingagora.network.Api
import com.example.livestreamingagora.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LiveRepository(private val application: Application) {
    private val apiService: ApiService

    init {
        apiService = Api.getInstance().apiService
    }

    fun userLive(callBack: LiveCallBack, body: LiveBody?) {
        val call = apiService.userLive(body)
        call?.enqueue(object : Callback<LiveResponse?> {
            override fun onResponse(call: Call<LiveResponse?>, response: Response<LiveResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    callBack.onResponse(response.body())
                } else {
                    callBack.onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<LiveResponse?>, t: Throwable) {
                callBack.onFailure(t.message)
            }
        })
    }

    interface LiveCallBack {
        fun onResponse(response: LiveResponse?)
        fun onFailure(message: String?)
    }
}
