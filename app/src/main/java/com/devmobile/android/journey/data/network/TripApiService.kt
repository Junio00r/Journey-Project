package com.devmobile.android.journey.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TripApiService {

    private const val BASE_URL = "https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws"

    private val clientHttp = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(clientHttp)
        .baseUrl(BASE_URL)
        .build()

    val service: ITripApiService by lazy {
        retrofit.create(ITripApiService::class.java)
    }
}