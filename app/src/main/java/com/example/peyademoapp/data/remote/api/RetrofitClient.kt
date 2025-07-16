package com.example.peyademoapp.data.remote.api


import com.example.peyademoapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val apiUrl = BuildConfig.API_BASE_URL

    init {
        println("API Base URL: $apiUrl")
    }

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    val apiInstance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}