package com.example.myapplicationtest.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class NewsPaperResponse (
    var status: String ,
    var totalResults: Int ,
    var articles: List<Article>
    )