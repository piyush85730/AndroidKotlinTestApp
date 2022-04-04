package com.demo.nytimes.di

import com.demo.nytimes.repository.ArticleRepository
import com.demo.nytimes.repository.ArticleRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ArticleRepository> { ArticleRepositoryImpl() }
}