package com.demo.nytimes.retrofit

import com.demo.nytimes.model.ArticleData
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {

    @GET("7.json?api-key=giAYecJqcMxBq4bW9NYxEwTxgmFdVAvl")
    fun getAllArticles(): Call<ArticleData>

}