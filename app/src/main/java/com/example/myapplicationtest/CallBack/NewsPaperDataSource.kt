package com.example.myapplicationtest.CallBack

import com.example.myapplicationtest.data.APISource
import com.example.myapplicationtest.data.NewsPaperResponse
import com.example.myapplicationtest.data.NewsSource
import com.example.myapplicationtest.data.Source

interface NewsPaperDataSource {

    interface getNewsPaperSources{

        fun getNewsPaperSourcesList(newsSources:  MutableList<NewsSource> )

        fun getError()

    }

    interface getNewsPaperSourceByName{

        fun getNewsPaperByName(newsPaperResponse: NewsPaperResponse)

        fun getError()

    }

}