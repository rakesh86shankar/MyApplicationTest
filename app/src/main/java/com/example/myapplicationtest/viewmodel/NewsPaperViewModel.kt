package com.example.myapplicationtest.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationtest.CallBack.NewsPaperDataSource.getNewsPaperSourceByName
import com.example.myapplicationtest.CallBack.NewsPaperDataSource.getNewsPaperSources
import com.example.myapplicationtest.data.APISource
import com.example.myapplicationtest.data.NewsPaperResponse
import com.example.myapplicationtest.data.NewsSource
import com.example.myapplicationtest.repository.NewsPaperRepository

class NewsPaperViewModel: ViewModel(), getNewsPaperSources, getNewsPaperSourceByName{


    val newsPaperRepository = NewsPaperRepository()
    val newsPapersList = MutableLiveData<List<NewsSource>>()
    val newsPaperObject = MutableLiveData<NewsPaperResponse>()


    fun getNewsPaperSourcesList(){
        newsPaperRepository.getNewsPaperSources(this)
    }

    override  fun getNewsPaperSourcesList(newsSources: MutableList<NewsSource>) {
        //newsPapersList.value = newsSources
        newsPapersList.postValue(newsSources)
    }


    override fun getError() {

    }

    fun getNewsPaperObject(newsSource:String){
        newsPaperRepository.getNewsPaperByName(newsSource,this)
    }

    override fun getNewsPaperByName(newsPaperResponse: NewsPaperResponse) {
        newsPaperObject.value = newsPaperResponse
    }



}