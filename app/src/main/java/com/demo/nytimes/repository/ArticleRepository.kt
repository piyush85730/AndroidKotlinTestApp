package com.demo.nytimes.repository

import androidx.lifecycle.MutableLiveData
import com.demo.nytimes.model.ArticleData

interface ArticleRepository {
    fun getAllArticle(resultLiveData: MutableLiveData<ArticleData>)

}