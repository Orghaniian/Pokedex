package com.orghaniian.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
 internal data class NamedAPIResource (
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

 internal typealias ColorResource = NamedAPIResource
 internal typealias TypeResource = NamedAPIResource
 internal typealias LanguageResource = NamedAPIResource
