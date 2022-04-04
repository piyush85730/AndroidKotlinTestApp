package com.demo.nytimes.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


open class RetrofitClientInstance {

    private var retrofit: Retrofit? = null

    open fun getRetrofitInstance(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/viewed/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder)
            .build()
        return retrofit
    }


    private var clientBuilder: OkHttpClient =
        OkHttpClient.Builder().addInterceptor(getInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS).build()

    private fun getInterceptor(): Interceptor {


        return Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
            val request = builder
                .header("Content-Type", "application/json")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
    }

}


