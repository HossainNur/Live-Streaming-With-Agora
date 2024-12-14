package com.example.livestreamingagora.network

import com.example.livestreamingagora.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A singleton class for {@link retrofit2}
 */
class Api private constructor() {
    @JvmField
    val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    companion object {
        @Volatile
        private var instance: Api? = null

        fun getInstance(): Api {
            return instance ?: synchronized(this) {
                instance ?: Api().also { instance = it }
            }
        }
    }

    fun getApiService(): ApiService {
        return apiService
    }
}
