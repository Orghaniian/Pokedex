package com.orghaniian.pokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("types") val types: List<PokemonType>,
    @SerializedName("sprites") val sprites: PokemonSprites,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
)

data class PokemonType(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: TypeResource
)

data class PokemonSprites(
     @SerializedName("other") val other: Other
)

data class Other(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default") val frontDefault: String
)
