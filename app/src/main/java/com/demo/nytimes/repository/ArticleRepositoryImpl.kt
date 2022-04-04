package com.demo.nytimes.repository

import androidx.lifecycle.MutableLiveData
import com.demo.nytimes.model.ArticleData
import com.demo.nytimes.retrofit.GetDataService
import com.demo.nytimes.retrofit.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ArticleRepositoryImpl : ArticleRepository {

    override fun getAllArticle(resultLiveData: MutableLiveData<ArticleData>) {
        val retrofitClientInstance =
            RetrofitClientInstance().getRetrofitInstance()?.create(GetDataService::class.java)
        retrofitClientInstance?.getAllArticles()?.enqueue(object : Callback<ArticleData> {
            override fun onResponse(call: Call<ArticleData>, response: Response<ArticleData>) {
                resultLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<ArticleData>, t: Throwable) {
                resultLiveData.postValue(throw Exception())
            }
        })
    }

}