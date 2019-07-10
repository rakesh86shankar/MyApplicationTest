package com.example.myapplicationtest.repository

import com.example.myapplicationtest.CallBack.NewsPaperDataSource
import com.example.myapplicationtest.data.APISource
import com.example.myapplicationtest.data.NewsPaperResponse
import com.example.myapplicationtest.network.retrofit
import com.example.myapplicationtest.newsAPIKey
import com.example.myapplicationtest.services.NewsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class NewsPaperRepository: BaseRepository() {
    var bgWorkerScope = CoroutineScope(Dispatchers.IO)
    val newsAPI : NewsAPI = retrofit().create(NewsAPI::class.java)

    fun getNewsPaperSources(
        callback: NewsPaperDataSource.getNewsPaperSources
    ){
        bgWorkerScope.async {
            //val response : APISource = newsAPI.getNewsSource(APIKey = newsAPIKey).await().body()!!
            val sourceResponse = safeApiCall( {newsAPI.getNewsSource(APIKey = newsAPIKey).await()},
                "Error Fetching News Paper Sources")
            if(sourceResponse?.sources?.size!! > 0){
                callback.getNewsPaperSourcesList(sourceResponse.sources.toMutableList())
            }else
                callback.getError()
        }
    }


    fun getNewsPaperByName(
        paperName:String ,
        callback: NewsPaperDataSource.getNewsPaperSourceByName
    ){
        bgWorkerScope.async {
            //val response : NewsPaperResponse = newsAPI.getNewsPaperByName(sources = paperName ,APIKey = newsAPIKey).await().body()!!
            //callback.getNewsPaperByName(response)
            val newsPaperSource = safeApiCall({ newsAPI.getNewsPaperByName(sources = paperName ,APIKey = newsAPIKey).await() },
                "Error Fetching News Paper"+paperName)
            if (newsPaperSource != null) {
                callback.getNewsPaperByName(newsPaperSource)
            }else
                callback.getError()

        }
    }
}