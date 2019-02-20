package com.example.d3companion.models

data class D3Build(
    val id: String,
    val name: String,
    val className: String,
    val url: String,
    val gear: List<D3Item>? = null,
    val gem: List<D3Item>? = null,
    val cube: List<D3Item>? = null
)
