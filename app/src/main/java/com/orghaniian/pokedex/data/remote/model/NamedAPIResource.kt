package com.orghaniian.pokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class NamedAPIResource (
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

typealias ColorResource = NamedAPIResource
typealias TypeResource = NamedAPIResource
typealias LanguageResource = NamedAPIResource
