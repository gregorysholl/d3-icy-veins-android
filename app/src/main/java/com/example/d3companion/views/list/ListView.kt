package com.example.d3companion.views.list

import com.example.d3companion.models.D3ViewElement

interface ListView {

    fun showList(list: List<D3ViewElement>)

    fun showError()
}