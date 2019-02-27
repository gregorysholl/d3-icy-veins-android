package com.example.d3companion.services

interface D3IcyVeinsContract {

    interface Provider {

        var listener: Listener?

        fun obtainData()
    }

    interface Listener {

        fun onDataRetrieved(data: String)

        fun onError(message: String)
    }
}
