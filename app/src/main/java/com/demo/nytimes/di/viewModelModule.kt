package com.demo.nytimes.di

import com.demo.nytimes.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ArticleViewModel(get()) }

}