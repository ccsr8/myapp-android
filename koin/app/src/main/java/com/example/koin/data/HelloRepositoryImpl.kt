package com.example.koin.data

import com.example.koin.data.HelloRepository

class HelloRepositoryImpl : HelloRepository {
    override fun giveHello() = "Hello Koin"
}