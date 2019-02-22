package com.example.d3companion.services

interface D3IcyVeinsProvider {
    fun obtainData()
    val listener: D3IcyVeinsListener
}

interface D3IcyVeinsListener {

    fun onDataRetrieved(data: String)

    fun onError(message: String)
}