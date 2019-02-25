package com.example.d3companion.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class D3Details(
    val name: String,
    val image: String,
    val descriptionIframe: String?
) : Parcelable
