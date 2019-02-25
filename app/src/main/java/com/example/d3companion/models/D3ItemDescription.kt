package com.example.d3companion.models

import com.google.gson.annotations.SerializedName

//TODO: See if there is a more elegant way to express cube-related descriptions
enum class D3ItemDescription {
    //character-related
    @SerializedName("amulet") Amulet,
    @SerializedName("belt") Belt,
    @SerializedName("boots") Boots,
    @SerializedName("bracers") Bracers,
    @SerializedName("chest") Chest,
    @SerializedName("gloves") Gloves,
    @SerializedName("helmet") Helmet,
    @SerializedName("offhand") Offhand,
    @SerializedName("pants") Pants,
    @SerializedName("ring") Ring,
    @SerializedName("shoulders") Shoulders,
    @SerializedName("weapon") Weapon,

    //gem-related
    @SerializedName("gem") Gem,

    //cube-related
    @SerializedName("cube-armor") CubeArmor,
    @SerializedName("cube-jewelry") CubeJewelry,
    @SerializedName("cube-weapon") CubeWeapon
}
