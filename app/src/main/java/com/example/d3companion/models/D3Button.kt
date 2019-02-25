package com.example.d3companion.models

import com.google.gson.annotations.SerializedName

enum class D3Button {
    @SerializedName("Left Mouse Button") LeftMouse,
    @SerializedName("Right Mouse Button") RightMouse,
    @SerializedName("1") FirstKey,
    @SerializedName("2") SecondKey,
    @SerializedName("3") ThirdKey,
    @SerializedName("4") FourthKey
}
