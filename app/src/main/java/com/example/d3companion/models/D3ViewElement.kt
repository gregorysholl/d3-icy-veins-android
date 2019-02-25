package com.example.d3companion.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class D3ViewElement(
    val name: String,
    val type: D3ViewType
) : Parcelable
