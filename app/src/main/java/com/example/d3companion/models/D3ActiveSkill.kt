package com.example.d3companion.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class D3ActiveSkill(
    val button: D3Button,
    val skill: D3Details,
    val rune: D3Details
) : Parcelable
