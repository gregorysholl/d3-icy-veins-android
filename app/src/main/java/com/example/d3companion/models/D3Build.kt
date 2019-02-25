package com.example.d3companion.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class D3Build(
    val id: String,
    val name: String,
    @SerializedName("d3-class-name") val className: String,
    val tier: String,
    val url: String,
    val skills: D3SkillSet,
    val gear: List<D3Item>? = null,
    val gem: List<D3Item>? = null,
    val cube: List<D3Item>? = null
) : Parcelable
