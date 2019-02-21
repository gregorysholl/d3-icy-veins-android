package com.example.d3companion.services

interface D3IcyVeinsProvider {

    fun obtainData()
}

interface D3IcyVeinsListener {

    fun onDataRetrieved(data: String)

    fun onError(message: String)
}