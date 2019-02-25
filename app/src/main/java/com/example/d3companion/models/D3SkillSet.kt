package com.example.d3companion.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class D3SkillSet(
    val active: List<D3ActiveSkill>,
    val passive: List<D3Details>
) : Parcelable
