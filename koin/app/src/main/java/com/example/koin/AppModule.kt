package com.example.koin

import org.koin.dsl.module

val appModule = module {

    // Single instance
    single<HelloRepository> { HelloRepositoryImpl() }

    // Factory
    factory { MySimplePresenter(get()) }

}