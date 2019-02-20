package com.example.d3companion.presenters.list

import com.example.d3companion.models.D3Type

interface ListPresenterProvider {

    fun getItems(type: D3Type)
}