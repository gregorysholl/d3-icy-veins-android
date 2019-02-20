package com.example.d3companion.views.list

import com.example.d3companion.models.D3Item

interface ListView {

    fun showList(list: List<D3Item>)

    fun showError()
}