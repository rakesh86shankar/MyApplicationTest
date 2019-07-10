package com.example.myapplicationtest.network

import com.example.myapplicationtest.newsAPIKey
import com.example.myapplicationtest.services.NewsAPI
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val authInterceptor = Interceptor {chain->
    val newUrl = chain.request().url()
        .newBuilder()
        .addQueryParameter("api_key", newsAPIKey)
        .build()

    val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()

    chain.proceed(newRequest)
}

//OkhttpClient for building http request url
private val newsAPIClient = OkHttpClient().newBuilder()
    .addInterceptor(authInterceptor)
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()



fun retrofit() : Retrofit = Retrofit.Builder()
    .client(newsAPIClient)
    .baseUrl("https://newsapi.org/")
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

