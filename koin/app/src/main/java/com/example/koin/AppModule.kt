package com.example.koin

import com.example.koin.data.HelloRepository
import com.example.koin.data.HelloRepositoryImpl
import com.example.koin.ui.MySimplePresenter
import org.koin.dsl.module

val appModule = module {

    // single instance of HelloRepository
    single<HelloRepository> { HelloRepositoryImpl() }

    // Simple Presenter Factory
    factory { MySimplePresenter(get()) }
}