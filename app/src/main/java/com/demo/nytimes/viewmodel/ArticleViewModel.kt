package com.demo.nytimes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.nytimes.model.ArticleData
import com.demo.nytimes.repository.ArticleRepository

class ArticleViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    val articleLiveData = MutableLiveData<ArticleData>()
    fun getAllArticle() {
        articleRepository.getAllArticle(articleLiveData)
    }

}