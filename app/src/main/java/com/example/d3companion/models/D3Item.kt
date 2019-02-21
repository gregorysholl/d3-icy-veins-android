package com.example.d3companion.models

data class D3Item(
    val description: D3ItemDescription,
    val main: D3Details,
    val alternatives: List<D3Details>
)
