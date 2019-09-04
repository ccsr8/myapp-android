package com.example.koin.ui

import com.example.koin.data.HelloRepository

class MySimplePresenter(val repo: HelloRepository) {

    fun sayHello() = "${repo.giveHello()} from $this"

}