package com.example.myapplicationtest.services

import com.example.myapplicationtest.data.APISource
import com.example.myapplicationtest.data.NewsPaperResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI{
    @GET("v2/sources")
    fun getNewsSource(@Query("apiKey") APIKey: String): Deferred<Response<APISource>>

    @GET("v2/top-headlines")
    fun getNewsPaperByName(@Query("sources") sources:String,
                           @Query("apiKey") APIKey: String): Deferred<Response<NewsPaperResponse>>


    //https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a3b94d1e4cab435d8096cc0f20060b96
}