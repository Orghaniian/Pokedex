package com.orghaniian.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
 internal data class Pokemon(
    @SerialName("name") val name: String,
    @SerialName("id") val id: Int,
    @SerialName("types") val types: List<PokemonType>,
    @SerialName("sprites") val sprites: PokemonSprites,
    @SerialName("height") val height: Int,
    @SerialName("weight") val weight: Int,
    @SerialName("stats") val stats: List<PokemonStat>,
)

@Serializable
 internal data class PokemonType(
    @SerialName("slot") val slot: Int,
    @SerialName("type") val type: TypeResource
)

@Serializable
 internal data class PokemonSprites(
     @SerialName("other") val other: Other
)

@Serializable
 internal data class Other(
    @SerialName("official-artwork") val officialArtwork: OfficialArtwork
)

@Serializable
 internal data class OfficialArtwork(
    @SerialName("front_default") val frontDefault: String
)

@Serializable
internal data class PokemonStat(
    @SerialName("stat") val stat: NamedAPIResource,
    @SerialName("effort") val effort: Int,
    @SerialName("base_stat") val baseState: Int,
)
