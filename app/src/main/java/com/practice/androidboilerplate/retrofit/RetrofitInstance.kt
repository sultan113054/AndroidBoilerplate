package com.practice.androidboilerplate.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {
        val BASE_URl = "https://jsonplaceholder.typicode.com/"
        var interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        var client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
        }.build()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

    }
}