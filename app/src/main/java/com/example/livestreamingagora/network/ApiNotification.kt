package com.example.livestreamingagora.network

import com.example.livestreamingagora.BASE_URL_NOTIFICATION
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiNotification private constructor() {
    @JvmField
    val apiService: ApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NOTIFICATION)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    companion object {
        @JvmStatic
        @Volatile
        var instance: ApiNotification? = null
            get() {
                if (field == null) synchronized(ApiNotification::class.java) {
                    if (field == null) {
                        field = ApiNotification()
                    }
                }
                return field
            }
            private set
    }
}
