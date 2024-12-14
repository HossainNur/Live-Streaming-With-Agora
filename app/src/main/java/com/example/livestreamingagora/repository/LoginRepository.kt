package com.example.livestreamingagora.repository

import android.app.Application
import android.util.Log
import com.example.livestreamingagora.ACCESS_TOKEN
import com.example.livestreamingagora.getSharedPref
import com.example.livestreamingagora.models.DeActiveResponse
import com.example.livestreamingagora.models.LoginBody
import com.example.livestreamingagora.models.LoginResponse
import com.example.livestreamingagora.models.LogoutResponse
import com.example.livestreamingagora.network.Api
import com.example.livestreamingagora.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(private val application: Application) {
    private val TAG = "LoginRepository"
    private val apiService: ApiService

    init {
        apiService = Api.getInstance().apiService
    }

    fun userLogin(callBack: LoginCallBack, body: LoginBody?) {
        val call = apiService.userLogin(body)
        call?.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>, response: Response<LoginResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.v(TAG, response.body().toString())
                    callBack.onResponse(response.body())
                } else {
                    Log.v(TAG, response.message())
                    callBack.onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                Log.v(TAG, "onFailure: " + t.message)
                callBack.onFailure(t.message)
            }
        })
    }

    fun userLogout(callBack: LoginCallBack) {
        val token = "Bearer " + getSharedPref(application, ACCESS_TOKEN)

        val call = apiService.userLogout(token)
        call?.enqueue(object : Callback<LogoutResponse?> {
            override fun onResponse(
                call: Call<LogoutResponse?>, response: Response<LogoutResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.v(TAG, response.body().toString())
                    callBack.onLogoutResponse(response.body())
                } else {
                    Log.v(TAG, response.message())
                    callBack.onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<LogoutResponse?>, t: Throwable) {
                callBack.onFailure(t.message)
            }
        })
    }

    fun deActiveUser(callBack: LoginCallBack, channelName: String?) {
        val call = apiService.deActiveUser(channelName)
        call?.enqueue(object : Callback<DeActiveResponse?> {
            override fun onResponse(
                call: Call<DeActiveResponse?>, response: Response<DeActiveResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.v(TAG, response.body().toString())
                    callBack.onDeActiveResponse(response.body())
                } else {
                    Log.v(TAG, response.message())
                    callBack.onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<DeActiveResponse?>, t: Throwable) {
                callBack.onFailure(t.message)
            }
        })
    }

    interface LoginCallBack {
        fun onResponse(response: LoginResponse?)
        fun onLogoutResponse(response: LogoutResponse?)
        fun onDeActiveResponse(response: DeActiveResponse?)
        fun onFailure(message: String?)
    }
}
