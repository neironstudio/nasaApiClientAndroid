package com.example.nasaexample.simplecoinexample

class MySimplePresenter (val repo: HelloRepository) {

    fun sayHello() = "${repo.giveHello()} from $this"
}