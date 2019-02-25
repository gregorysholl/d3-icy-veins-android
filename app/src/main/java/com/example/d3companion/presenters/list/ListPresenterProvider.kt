package com.example.d3companion.presenters.list

interface ListPresenterProvider {

    fun getClasses()

    fun getBuilds(className: String)
}